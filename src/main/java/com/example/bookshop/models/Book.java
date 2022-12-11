package com.example.bookshop.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Enumerated (EnumType.STRING)
    private AgeRestriction ageRestriction;

    @Column (nullable = false)
    private int copies;

    @Column (length = 1000)
    private String description;

    @Enumerated (EnumType.STRING)
    private EditionType editionType;

    @Column (length = 19, precision = 2, nullable = false)
    private BigDecimal price;

    @Column (name = "release_date")
    private LocalDate releaseDate;

    @Column (length = 50, nullable = false)
    private String title;

    @ManyToOne (cascade = CascadeType.DETACH)
    private Author author;

    @ManyToMany (cascade = CascadeType.DETACH)
    private Set<Category> categories;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBookTitleAndPriceFormat() {
        return this.title + " " + this.price + " BGN";
    }
}
