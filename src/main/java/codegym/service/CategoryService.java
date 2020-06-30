package codegym.service;

import codegym.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    void remove(Long id);
}
