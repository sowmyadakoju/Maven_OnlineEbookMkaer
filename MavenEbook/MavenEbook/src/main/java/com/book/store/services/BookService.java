package com.book.store.services;

import com.book.store.dto.CreateBookDTO;
import com.book.store.dto.UpdateBookDTO;
import com.book.store.dto.UpdateBookReqDTO;
import com.book.store.models.Book;
import com.book.store.models.BookRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public List<Book> allBooks();
    public Book getBook(Long bookId);
    public void deleteBook(Long bookId);
    public List<Book> finishedBooks();
    public List<Book> publishedBooks();
    public List<Book> myBooks(Long userId);
    public Book createBook(CreateBookDTO r);
    public Book updateBook(UpdateBookDTO r);

    public BookRequest createBookRequest(Long bookId);
    public void deleteBookRequest(Long bookReqId);
    public List<BookRequest> allBookRequests();
    public List<BookRequest> myBookRequests(Long userId);
    public BookRequest updateBookRequest(UpdateBookReqDTO r);
}
