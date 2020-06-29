package codegym.controller;


import codegym.model.Blog;
import codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.Timestamp;
import java.util.List;

@Controller
@EnableWebMvc
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/")
    public ModelAndView showIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        Iterable blogList = blogService.findAll();
        modelAndView.addObject("blogs",blogList);
        return modelAndView;
    }

    @ModelAttribute("blogList")
    public Iterable<Blog> getBlogs(){
        Iterable<Blog> blogs = blogService.findAll();
        return blogs;
    }

    @GetMapping("/create")
    public ModelAndView showWriteForm(){
        ModelAndView mav = new ModelAndView("create","blog",new Blog());
        return mav;
    }

    @PostMapping("/submitBlog")
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
