package com.book.store.dto;

import com.book.store.enums.BookStatusEnum;



public class UpdateBookReqDTO {
    private Long bookIdReq;
    private BookStatusEnum bookStatus;

    public UpdateBookReqDTO(Long bookIdReq, BookStatusEnum bookStatus) {
        this.bookIdReq = bookIdReq;
        this.bookStatus = bookStatus;
    }

    public UpdateBookReqDTO() {
    }

    public Long getBookIdReq() {
        return bookIdReq;
    }

    public void setBookIdReq(Long bookIdReq) {
        this.bookIdReq = bookIdReq;
    }

    public BookStatusEnum getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatusEnum bookStatus) {
        this.bookStatus = bookStatus;
    }
}
