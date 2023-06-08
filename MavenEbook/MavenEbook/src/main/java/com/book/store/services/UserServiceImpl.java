package com.book.store.services;

import com.book.store.dao.BookDAO;
import com.book.store.dao.BookRequestDAO;
import com.book.store.dao.UserDAO;
import com.book.store.dto.UpdateAuthorDTO;
import com.book.store.exceptions.ResourceNotFoundException;
import com.book.store.models.Book;
import com.book.store.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BookRequestDAO bookRequestDAO;
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getAuthorDetails(Long userId) {
        return userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = userDAO.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
        List<Book> books = bookDAO.findByUserId(userId);
        for(Book book:books){
            bookRequestDAO.deleteByBook(book);
        }
        bookDAO.deleteAll(books);
        userDAO.delete(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
    
    @Override

    public User updateAuthor(UpdateAuthorDTO r) {

    User user=userDAO.findById(r.getId()).orElseThrow(() ->new ResourceNotFoundException("User not found."));

    user.setFirstName(r.getFirstName());

    user.setLastName(r.getLastName());

    user.setEmail(r.getEmail());

    user.setPassword(passwordEncoder.encode(r.getPassword()));

    user.setActive(r.getActive());

    userDAO.save(user);

    return user;

    }
}
