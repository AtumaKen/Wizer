package com.wizer.wizer_test.service;

import com.wizer.wizer_test.exceptions.NotFoundException;
import com.wizer.wizer_test.helper.Validations;
import com.wizer.wizer_test.io.entities.Category;
import com.wizer.wizer_test.io.repositories.CategoryRepository;
import com.wizer.wizer_test.models.request.CategoryRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CatergoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Validations validations;

    public Category saveCategory(CategoryRequest categoryRequest) {
        validations.validateCategory(categoryRequest);
        var category = mapper.map(categoryRequest, Category.class);
        return categoryRepository.save(category);
    }

    public Category updateCategory(long id, CategoryRequest request) {
        validations.validateCategory(request);

        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("404", "Category Not found"));

        mapper.map(request, category);
        //todo: validate here too
        return categoryRepository.save(category);
    }

    public Page<Category> getAll(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }
}
