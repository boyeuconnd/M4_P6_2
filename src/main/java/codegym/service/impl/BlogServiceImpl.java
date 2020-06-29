package codegym.service.impl;

import codegym.model.Blog;
import codegym.repository.BlogRepository;
import codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public Iterable findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);

    }

    @Override
    public void remove(Long id) {
        blogRepository.delete(id);

    }
}
