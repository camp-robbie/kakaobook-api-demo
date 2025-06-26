package com.camp.kakaobookapidemo.controller;

import com.camp.kakaobookapidemo.dto.BookRequest;
import com.camp.kakaobookapidemo.dto.KakaoBookResponse;
import com.camp.kakaobookapidemo.entity.BookEntity;
import com.camp.kakaobookapidemo.service.BookService;
import com.camp.kakaobookapidemo.service.KakaoBookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookSearchController {
    private final KakaoBookSearchService kakaoBookSearchService;
    private final BookService bookService;

    @GetMapping("/search")
    public KakaoBookResponse search(@RequestParam("q") String query, @RequestParam(defaultValue = "10") int size) {
        return kakaoBookSearchService.searchBooks(query, size);
    }

    @PostMapping("/api/books")
    public ResponseEntity<BookEntity> saveBook(@RequestBody BookRequest request) {
        BookEntity bookEntity = bookService.save(request);
        return ResponseEntity.ok(bookEntity);
    }
//
//    @GetMapping("/api/books")
//    public ResponseEntity<List<BookEntity>> getBooks() {
//        return ResponseEntity.ok(bookService.getBooks());
//    }
}
