package com.example.bookshop.repositories;

import com.example.bookshop.models.AgeRestriction;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    Optional<List<Book>> findBooksByCopiesLessThan(int copies);

    Optional<List<Book>> findBooksByReleaseDateNotLike(LocalDate date);

    Optional<List<Book>> findBooksByTitleContaining(String text);

    @Query("select count(b) from Book b where length(b.title) > :number ")
   int countBookByTitleIsGreaterThan(int number);


    Optional<List<Book>>

}
