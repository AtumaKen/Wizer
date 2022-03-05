package com.wizer.wizer_test.controllers;


import com.wizer.wizer_test.models.request.BookRequest;

import com.wizer.wizer_test.models.response.BaseResponse;
import com.wizer.wizer_test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;


    @PostMapping
    public BaseResponse createBook(@RequestBody @Valid BookRequest request) {
        var book = service.createBook(request);
        return BaseResponse.builder()
                .code("201")
                .description("Book created successfully")
                .data(book).build();
    }

    @PutMapping("/{id}")
    public BaseResponse updateBook(@PathVariable long id, @RequestBody @Valid BookRequest request) {
        var category = service.updateBook(id, request);
        return BaseResponse.builder()
                .code("204")
                .description("Book updated successfully")
                .data(category).build();
    }

    @GetMapping("/list")
    public BaseResponse getAllBooks(@RequestParam(name = "page") int page,
                                    @RequestParam(name = "size") int size) {
        var categories = service.getAll(page, size);
        return BaseResponse.builder()
                .code("200")
                .description("Result fetched successfully")
                .data(categories).build();
    }

    @GetMapping("/favorites")
    public BaseResponse getFavorites() {
        var categories = service.getFavorites();
        return BaseResponse.builder()
                .code("200")
                .description("Result fetched successfully")
                .data(categories).build();
    }
}