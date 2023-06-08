package com.book.store.models;

import com.book.store.enums.BookStatusEnum;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Book book;
    private LocalDateTime requestedAt;
    private BookStatusEnum bookStatus;
    private String remark;

    public BookRequest(Long id, Book book, LocalDateTime requestedAt, BookStatusEnum bookStatus, String remark) {
        this.id = id;
        this.book = book;
        this.requestedAt = requestedAt;
        this.bookStatus = bookStatus;
        this.remark = remark;
    }

    public BookRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public BookStatusEnum getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatusEnum bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
