package com.example.bookshop;

import com.example.bookshop.models.Book;
import com.example.bookshop.services.AuthorService;
import com.example.bookshop.services.BookService;
import com.example.bookshop.services.CategoryService;
import com.example.bookshop.services.SeedService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final Scanner scanner;
    private final BookService bookService;
    private final AuthorService authorService;


    @Autowired
    public ConsoleRunner(BookService bookService, AuthorService authorService) {

        this.bookService = bookService;
        this.authorService = authorService;
        this.scanner = new Scanner (System.in);
    }

    @Override
    public void run(String... args) throws Exception {
 //    String text = scanner.nextLine();



//
//        bookService.findBooksByAgeRestriction(ageRestriction)
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);


//        bookService.findBooksByCopiesLessThan(numberOfCopies)
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);


//        bookService.findBooksByReleaseDateNotLike(LocalDate.parse("1992-04-12"))
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);


//        bookService.findBooksByTitleContaining(text)
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

//        int number = Integer.parseInt(scanner.nextLine());
//        System.out.println(bookService.countBookByTitleIsGreaterThan(number));

    }
}
