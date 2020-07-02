package codegym.service;

import codegym.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);

    Blog findById(Long id);

    Blog save(Blog blog);

    void remove(Long id);

    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
}
