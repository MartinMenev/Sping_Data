package com.example.bookshop.services;

import com.example.bookshop.models.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();

    boolean isDataSeeded();

    void seedCategories(List<Category> collect);
}
