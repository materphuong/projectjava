package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.dto.BookDto;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/books")

public class ApiController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    private  BookDto convertToBookDto(Book book){
        BookDto bookDTO = new BookDto();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setCategoryName(categoryService.getCategoryById(book.getCategory().getId()).getName());
        return bookDTO;
    }
    @GetMapping
    @ResponseBody
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks().stream().map(this::convertToBookDto).toList();
    }
    @GetMapping("/{id}")
    @ResponseBody
    public BookDto getBookById(@PathVariable Long id){
        return convertToBookDto(bookService.getBookById(id));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteBook(@PathVariable Long id){
        if(bookService.getBookById(id) != null)
            bookService.deleteBook(id);
    }
}
