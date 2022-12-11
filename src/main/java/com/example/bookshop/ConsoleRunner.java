package com.example.bookshop;

import com.example.bookshop.services.AuthorService;
import com.example.bookshop.services.BookService;
import com.example.bookshop.services.CategoryService;
import com.example.bookshop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final Scanner scanner;
    private final BookService bookService;
    private final AuthorService authorService;

    private final SeedService seedService;

    @Autowired
    public ConsoleRunner(BookService bookService, AuthorService authorService, SeedService seedService) {

        this.bookService = bookService;
        this.authorService = authorService;
        this.seedService = seedService;
        this.scanner = new Scanner (System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seeAllData();



    }
}
