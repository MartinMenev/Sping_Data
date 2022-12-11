package com.example.bookshop.services;

import com.example.bookshop.models.AgeRestriction;
import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.Category;
import com.example.bookshop.models.EditionType;
import com.example.bookshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.bookshop.constants.FilePath.*;

@Service
public class SeedServiceImpl implements SeedService {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (!this.authorService.isDataSeeded()) {
            this.authorService
                    .seedAuthors(Files.readAllLines(Path.of(RESOURCE_URL + AUTHORS_FILE_NAME))
                            .stream()
                            .filter(s -> !s.isBlank())
                            .map(firstAndLastName -> Author.builder()
                                    .firstName(firstAndLastName.split(" ")[0])
                                    .lastName(firstAndLastName.split(" ")[1])
                                    .build())
                            .collect(Collectors.toList()));
        }
    }


    @Override
    public void seedBooks() throws IOException {
        if (!this.bookService.isDataSeeded()) {
            final List<Book> books = Files.readAllLines(Path.of(RESOURCE_URL + BOOK_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(row -> {
                        String[] data = row.split("\\s+");
                        String title = Arrays.stream(data)
                                .skip(5)
                                .collect(Collectors.joining(" "));

                        return Book.builder()
                                .title(title)
                                .editionType(EditionType.values()[Integer.parseInt(data[0])])
                                .price(new BigDecimal(data[3]))
                                .releaseDate(LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy")))
                                .ageRestriction(AgeRestriction.values()[Integer.parseInt(data[4])])
                                .author(this.authorService.getRandomAuthor())
                                .categories(this.categoryService.getRandomCategories())
                                .copies(Integer.parseInt(data[2]))
                                .build();

                    })
                    .collect(Collectors.toList());

            this.bookService.seedBooks(books);
        }
    }

    @Override
    public void seedCategories() throws IOException {
        if (!this.categoryService.isDataSeeded()) {
            this.categoryService.seedCategories(Files.readAllLines(Path.of(RESOURCE_URL + CATEGORIES_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(name -> Category.builder()
                            .name(name)
                            .build())
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public void seeAllData() throws IOException {
        SeedService.super.seeAllData();
    }
}
