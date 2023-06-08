package com.book.store.services;

import com.book.store.dto.UpdateAuthorDTO;
import com.book.store.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User getAuthorDetails(Long userId);

    public void deleteUser(Long userId);

    public List<User> getAllUsers();
    
    public User updateAuthor(UpdateAuthorDTO r);
}
