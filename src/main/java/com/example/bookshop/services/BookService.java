package com.example.bookshop.services;

import com.example.bookshop.models.AgeRestriction;
import com.example.bookshop.models.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookService {


    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    List<Book> findBooksByAgeRestriction(String ageRestriction);

    List<Book> findBooksByCopiesLessThan(int copies);

    List<Book> findBooksByReleaseDateNotLike(LocalDate date);

    List<Book> findBooksByTitleContaining(String text);

    int countBookByTitleIsGreaterThan(int number);


    int updateBooksCopiesWithSpecificAuthor(int increaseNumber, LocalDate releaseDate);

}
