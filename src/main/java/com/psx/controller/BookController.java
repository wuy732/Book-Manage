package com.psx.controller;

import com.psx.entity.Book;
import com.psx.mapper.BookMapper;
import com.psx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/list")
    public String listBooks(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author,
            Model model) {

        model.addAttribute("page", bookService.getBooksByPage(current, size, name, author));
        return "bookListA";
    }

    @PostMapping("/add")
    public String addBook(Book book) {
        bookService.insert(book);
        return "redirect:/book/list";
    }


    @ResponseBody
    @PutMapping("/borrow/{id}")
    public ResponseEntity<Map<String, Object>> borrowBook(@PathVariable Integer id) {
        System.out.println("id = " + id);
        bookService.updateStatus(id);
        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        return ResponseEntity.ok(result);
    }
}