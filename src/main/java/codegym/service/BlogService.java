package codegym.service;

import codegym.model.Blog;

import java.util.List;

public interface BlogService {
    Iterable<Blog> findAll();

    Blog findById(Long id);

    Blog save(Blog blog);

    void remove(Long id);
}
