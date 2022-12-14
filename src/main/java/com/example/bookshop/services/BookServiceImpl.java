package com.example.bookshop.services;

import com.example.bookshop.models.AgeRestriction;
import com.example.bookshop.models.Book;
import com.example.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAll(books);
    }

    @Override
    public boolean isDataSeeded() {
        return bookRepository.count() > 0;
    }

    @Override
    public List<Book> findBooksByAgeRestriction(String restriction) {
        AgeRestriction input = AgeRestriction.valueOf(restriction.toUpperCase());
        return bookRepository.findBooksByAgeRestriction(input)
                .orElseThrow(NoSuchElementException::new);
    }


}
