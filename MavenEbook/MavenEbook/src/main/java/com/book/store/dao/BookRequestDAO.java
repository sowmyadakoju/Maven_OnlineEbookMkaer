package com.book.store.dao;

import com.book.store.models.Book;
import com.book.store.models.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRequestDAO extends JpaRepository<BookRequest, Long> {
    long deleteByBook(Book book);
    List<BookRequest> findByBookUserId(Long userId);
}
