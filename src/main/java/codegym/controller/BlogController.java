package codegym.controller;


import codegym.model.Blog;
import codegym.model.Category;
import codegym.service.BlogService;
import codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.Timestamp;
import java.util.Optional;

@Controller
@EnableWebMvc
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }



    @GetMapping("/")
    public ModelAndView showIndex(@RequestParam("s") Optional<String> keyword,@PageableDefault(size = 2)Pageable pageable){
        Page<Blog> blogList;
        ModelAndView modelAndView = new ModelAndView("index");
        if(keyword.isPresent()){

            blogList= blogService.findAllByTitleContaining(keyword.get(),pageable);
            modelAndView.addObject("keyword",keyword.get());
            if(!blogList.hasContent()){
                modelAndView.addObject("mess","No result");
            }
        }else {

            blogList= blogService.findAll(pageable);
        }
        modelAndView.addObject("blogs",blogList);
        return modelAndView;
    }

    @ModelAttribute("blogList")
    public Page<Blog> getBlogs(Pageable pageable){
        Page<Blog> blogs = blogService.findAll(pageable);
        return blogs;
    }

    @GetMapping("/blog/{id}")
    public ModelAndView showBlogDetail(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("blog-detail","blog",blogService.findById(id));

        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView showWriteForm(){
        ModelAndView mav = new ModelAndView("create","blog",new Blog());
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView submitNewBlog(@ModelAttribute("blog") Blog blog){
        blog.setCreateDate(new Timestamp(System.currentTimeMillis()));
        blogService.save(blog);
        ModelAndView mav = new ModelAndView("create","blog",new Blog());
        mav.addObject("mess","Submit successfully");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("update");
        mav.addObject("editBlog",blogService.findById(id));
        return mav;
    }

    @PostMapping("/editBlog")
    public String updateBlog(@ModelAttribute("editBlog") Blog blog){
        blogService.save(blog);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("delete");
        mav.addObject("deleteBlog",blogService.findById(id));
        return mav;
    }

    @PostMapping("/deleteBlog")
    public String deleteBlog(@ModelAttribute("deleteBlog")Blog blog){
        Long id = blog.getId();
        blogService.remove(id);
        return "redirect:/";
    }

}
