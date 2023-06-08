package com.book.store.services;

import com.book.store.dao.BookDAO;
import com.book.store.dao.BookRequestDAO;
import com.book.store.dao.UserDAO;
import com.book.store.dto.CreateBookDTO;
import com.book.store.dto.UpdateBookDTO;
import com.book.store.dto.UpdateBookReqDTO;
import com.book.store.enums.BookEditStatusEnum;
import com.book.store.enums.BookStatusEnum;
import com.book.store.exceptions.BookAlreadyExistsException;
import com.book.store.exceptions.BookLimitExceededException;
import com.book.store.exceptions.ResourceNotFoundException;
import com.book.store.models.Book;
import com.book.store.models.BookRequest;
import com.book.store.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class BookServiceImpl implements BookService{
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private BookRequestDAO bookRequestDAO;


    @Override
    public List<Book> allBooks() {
        return bookDAO.findAll();
    }

    @Override
    public Book getBook(Long bookId) {
        return bookDAO.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book not found"));
    }

    @Override
    public void deleteBook(Long bookId) {
        bookDAO.deleteById(bookId);
    }


    @Override
    public List<Book> finishedBooks() {
        return bookDAO.findByEditStatus(BookEditStatusEnum.COMPLETED);
    }

    @Override
    public List<Book> publishedBooks() {
        return bookDAO.findByBookStatus(BookStatusEnum.PUBLISHED);
    }

    @Override
    public List<Book> myBooks(Long userId) {
        return bookDAO.findByUserId(userId);
    }

    @Override
    public Book createBook(CreateBookDTO r) {
        User user = userDAO.findById(r.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = new Book();
       
        long currentWorkingCount = bookDAO.countByUserIdAndEditStatus(r.getUserId(), BookEditStatusEnum.WORKING);
        
        if(bookDAO.findByTitle(r.getTitle())!=null) {
        	throw new BookAlreadyExistsException("Book already exists");
        }
        
        if(currentWorkingCount >= 3 ) throw new BookLimitExceededException();
        
        book.setUser(user);
        book.setTitle(r.getTitle());
        book.setPrice(r.getPrice());
        book.setPages(r.getPages());
        book.setDescription(r.getDescription());
        book.setContent(r.getContent());
        book.setBookStatus(BookStatusEnum.PENDING);
        book.setEditStatus(BookEditStatusEnum.WORKING);
        book.setCreatedAt(LocalDateTime.now());
        bookDAO.save(book);
        return book;
    }

    @Override
    public Book updateBook(UpdateBookDTO r) {
        Book book = bookDAO.findById(r.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book not found."));
        book.setContent(r.getContent());
        book.setPages(r.getPages());
        book.setEditStatus(r.getEditStatus());
        bookDAO.save(book);
        return book;
    }

    @Override
    @Transactional
    public BookRequest createBookRequest(Long bookId) {
        Book book = bookDAO.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found."));
        book.setEditStatus(BookEditStatusEnum.COMPLETED);
        bookDAO.save(book);
        BookRequest bookRequest = new BookRequest(null, book, LocalDateTime.now(), BookStatusEnum.PENDING, "");
        bookRequestDAO.save(bookRequest);
        return bookRequest;
    }

    @Override
    @Transactional
    public void deleteBookRequest(Long bookReqId) {
        BookRequest bookRequest = bookRequestDAO.findById(bookReqId)
                .orElseThrow(() -> new ResourceNotFoundException("Book request not found"));
        Book book = bookDAO.findById(bookRequest.getBook().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setEditStatus(BookEditStatusEnum.WORKING);
        book.setBookStatus(BookStatusEnum.PENDING);
        bookDAO.save(book);
        bookRequestDAO.deleteById(bookReqId);
    }

    @Override
    public List<BookRequest> allBookRequests() {
        return bookRequestDAO.findAll();
    }

    @Override
    public List<BookRequest> myBookRequests(Long userId) {
        return bookRequestDAO.findByBookUserId(userId);
    }

    @Override
    @Transactional
    public BookRequest updateBookRequest(UpdateBookReqDTO r) {
        BookRequest bookRequest = bookRequestDAO.findById(r.getBookIdReq()).orElseThrow(() -> new ResourceNotFoundException("Book request not found."));
        bookRequest.setBookStatus(r.getBookStatus());
        Book book = bookRequest.getBook();
        book.setBookStatus(r.getBookStatus());
        bookDAO.save(book);
        bookRequest.setBook(book);
        bookRequestDAO.save(bookRequest);
        return bookRequest;
    }
}
