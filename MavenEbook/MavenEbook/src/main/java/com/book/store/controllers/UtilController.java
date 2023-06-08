package com.book.store.controllers;

import com.book.store.dto.BasicResponseDTO;
import com.book.store.enums.BookEditStatusEnum;
import com.book.store.enums.BookStatusEnum;
import com.book.store.enums.UserRoleEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/util")
public class UtilController {
    @GetMapping("/book-edit-status-enum")
    public ResponseEntity<BasicResponseDTO<BookEditStatusEnum[]>> getBookEditEnum(){
        return ResponseEntity.ok(new BasicResponseDTO<>(true, "Data", BookEditStatusEnum.values()));
    }
    @GetMapping("/book-status-enum")
    public ResponseEntity<BasicResponseDTO<BookStatusEnum[]>> getBookStatusEnum(){
        return ResponseEntity.ok(new BasicResponseDTO<>(true, "Data", BookStatusEnum.values()));
    }
    @GetMapping("/user-role-enum")
    public ResponseEntity<BasicResponseDTO<UserRoleEnum[]>> getUserRoleEnum(){
        return ResponseEntity.ok(new BasicResponseDTO<>(true, "Data", UserRoleEnum.values()));
    }
}
