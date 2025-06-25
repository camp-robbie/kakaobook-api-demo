package com.camp.kakaobookapidemo.repository;

import com.camp.kakaobookapidemo.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
