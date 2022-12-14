package com.example.bookshop.services;

import com.example.bookshop.models.AgeRestriction;
import com.example.bookshop.models.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookService {


    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    List<Book> findBooksByAgeRestriction(String ageRestriction);

}
