package com.camp.kakaobookapidemo.service;

import com.camp.kakaobookapidemo.dto.BookRequest;
import com.camp.kakaobookapidemo.entity.BookEntity;
import com.camp.kakaobookapidemo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookEntity save(BookRequest request) {
        BookEntity bookEntity = new BookEntity(request.getTitle());
        return bookRepository.save(bookEntity);
    }
}
