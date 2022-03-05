package com.wizer.wizer_test.controllers;

import com.wizer.wizer_test.io.entities.Category;
import com.wizer.wizer_test.models.request.CategoryRequest;
import com.wizer.wizer_test.models.response.BaseResponse;
import com.wizer.wizer_test.service.CatergoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    private CatergoryService service;

    @PostMapping
    public BaseResponse createCategory(@Valid @RequestBody CategoryRequest request) {
        var category = service.saveCategory(request);

        return BaseResponse.builder()
                .code("201")
                .description("Category created successfully")
                .data(category).build();
    }

    @PutMapping("/{id}")
    public BaseResponse updateCategory(@PathVariable long id, @RequestBody @Valid CategoryRequest request) {
        var category = service.updateCategory(id, request);
        return BaseResponse.builder()
                .code("204")
                .description("Category updated successfully")
                .data(category).build();
    }

    @GetMapping("/list")
    public BaseResponse getAllCategories(@RequestParam(name = "page") int page,
                                         @RequestParam(name = "size") int size) {
        var categories = service.getAll(page, size);
        return BaseResponse.builder()
                .code("200")
                .description("Result fetched successfully")
                .data(categories).build();
    }

}
