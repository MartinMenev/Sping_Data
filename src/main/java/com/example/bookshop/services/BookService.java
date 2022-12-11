package com.example.bookshop.services;

import com.example.bookshop.models.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {


    void seedBooks(List<Book> books);

    boolean isDataSeeded();

}
