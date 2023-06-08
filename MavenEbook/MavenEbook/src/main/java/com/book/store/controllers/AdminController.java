package com.book.store.controllers;

import com.book.store.dto.BasicResponseDTO;
import com.book.store.dto.UpdateBookReqDTO;
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
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/books")
    public ResponseEntity<BasicResponseDTO<List<Book>>> allBooks(){
        List<Book> books = bookService.allBooks();
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "All Books", books), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/book/{bookId}")
    public ResponseEntity<BasicResponseDTO<Book>> getBook(@PathVariable("bookId") Long bookId){
        Book book = bookService.getBook(bookId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Book", book), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/users")
    public ResponseEntity<BasicResponseDTO<List<User>>> allUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Users list", users), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/delete-book/{bookId}")
    public ResponseEntity<BasicResponseDTO<String>> deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Book successfully deleted", null), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/delete-book-request/{bookReqId}")
    public ResponseEntity<BasicResponseDTO<String>> deleteBookRequest(@PathVariable("bookReqId") Long bookReqId){
        bookService.deleteBookRequest(bookReqId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Book request successfully deleted", null), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<BasicResponseDTO<String>> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "User successfully deleted", null), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/finished-books")
    public ResponseEntity<BasicResponseDTO<List<Book>>> finishedBooks(){
        List<Book> books = bookService.finishedBooks();
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Finished books", books), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/published-books")
    public ResponseEntity<BasicResponseDTO<List<Book>>> publishedBooks(){
        List<Book> books = bookService.publishedBooks();
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Published books", books), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/book-requests")
    public ResponseEntity<BasicResponseDTO<List<BookRequest>>> allRequests(){
        List<BookRequest> bookRequests = bookService.allBookRequests();
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Books requests list", bookRequests), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/author-details/{userId}")
    public ResponseEntity<BasicResponseDTO<User>> authorDetails(@PathVariable("userId") Long userId){
        User user = userService.getAuthorDetails(userId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Author details", user), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/book-request-update")
    public ResponseEntity<BasicResponseDTO<BookRequest>> updateBookRequest(@RequestBody UpdateBookReqDTO r){
        BookRequest bookRequest = bookService.updateBookRequest(r);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Book request updated", bookRequest), HttpStatus.OK);
    }
}
