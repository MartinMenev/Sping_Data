package com.example.bookshop.services;

import com.example.bookshop.models.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {



    boolean isDataSeeded();

    void seedAuthors(List<Author> authors);

    Author getRandomAuthor();

}
