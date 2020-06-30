package codegym.controller;


import codegym.model.Category;
import codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @GetMapping(value = "/")
    public ResponseEntity<Iterable<Category>> listAllCategory(){
        Iterable<Category> categories = categoryService.findAll();
        if(categories == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity(categories,HttpStatus.OK);
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> createCategory(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity(category,HttpStatus.OK);
    }
}
