package com.book.store.controllers;

import com.book.store.dto.BasicResponseDTO;
import com.book.store.dto.CreateBookDTO;
import com.book.store.dto.UpdateAuthorDTO;
import com.book.store.dto.UpdateBookDTO;
import com.book.store.models.Book;
import com.book.store.models.BookRequest;
import com.book.store.models.User;
import com.book.store.services.BookService;
import com.book.store.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('AUTHOR')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/my-books/{userId}")
    public ResponseEntity<BasicResponseDTO<List<Book>>> myBooks(@PathVariable("userId") Long userId){
        List<Book> books = bookService.myBooks(userId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Books list", books), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('AUTHOR')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/published-books")
    public ResponseEntity<BasicResponseDTO<List<Book>>> publishedBooks(){
        List<Book> books = bookService.publishedBooks();
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Books list", books), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('AUTHOR')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/my-books-requests/{userId}")
    public ResponseEntity<BasicResponseDTO<List<BookRequest>>> myBookRequests(@PathVariable("userId") Long userId){
        List<BookRequest> bookRequests = bookService.myBookRequests(userId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Books request list", bookRequests), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('AUTHOR')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/book/{bookId}")
    public ResponseEntity<BasicResponseDTO<Book>> getBook(@PathVariable("bookId") Long bookId){
        Book book = bookService.getBook(bookId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Book", book), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/create-book")
    public ResponseEntity<BasicResponseDTO<Book>> createBook(@RequestBody CreateBookDTO r){
        Book book = bookService.createBook(r);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Book created", book), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('AUTHOR')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/update-book")
    public ResponseEntity<BasicResponseDTO<Book>> updateBook(@RequestBody UpdateBookDTO r){
        Book book = bookService.updateBook(r);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Book updated", book), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('AUTHOR')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/create-book-request/{bookId}")
    public ResponseEntity<BasicResponseDTO<BookRequest>> createBookRequest(@PathVariable("bookId") Long bookId){
        BookRequest bookRequest = bookService.createBookRequest(bookId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Book request created", bookRequest), HttpStatus.OK);
    }
    
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))

    @PutMapping("/Author-details-update")

    public ResponseEntity<BasicResponseDTO<User>> updateAuthor(@RequestBody UpdateAuthorDTO r){

    User userUpdate = userService.updateAuthor(r);

    return new ResponseEntity<>(new BasicResponseDTO<>(true, "User updated", userUpdate), HttpStatus.OK);

    }

}
