package com.example.bookshop.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    public Author() {
        this.books = new HashSet<>();
    }

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name", nullable = false)
    private String lastName;

    @OneToMany (targetEntity = Book.class, mappedBy = "author")
    private Set<Book> books;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
