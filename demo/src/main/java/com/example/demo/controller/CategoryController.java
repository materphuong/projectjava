package com.example.demo.controller;

import  com.example.demo.entity.Category;
import com.example.demo.services.CategoryService;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/categories")
public class CategoryController{
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBrands(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/listCategory";
    }
    @GetMapping("/addCategory")
    public String addBrandForm(Model model)
    {
        model.addAttribute("category",new Category());
        return "category/addCategory";
    }
    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category")Category category){
        categoryService.addCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/editCategory/{id}")
    public String editCategoryForm(@PathVariable("id") Long id, Model model){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/editCategory";
    }

    @PostMapping("/editCategory/{id}")
    public String editCategory(@PathVariable("id") Long id, @ModelAttribute("category") Category category){
        category.setId(id);
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
