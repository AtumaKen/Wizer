package com.wizer.wizer_test.helper;

import com.wizer.wizer_test.exceptions.NotFoundException;
import com.wizer.wizer_test.io.repositories.BookRepository;
import com.wizer.wizer_test.io.repositories.CategoryRepository;
import com.wizer.wizer_test.models.request.BookRequest;
import com.wizer.wizer_test.models.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validations {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void validateBook(BookRequest request){
        Boolean exists = bookRepository.existsByBookNameAndAuthorAndCategoryId(request.getBookName(),
                request.getAuthor(), request.getCategoryId());
        //throw bad request
        if(exists) throw new NotFoundException("404", "Book exists");
    }

    public void validateCategory(CategoryRequest request){
    Boolean exists = categoryRepository.existsByCategoryName(request.getCategoryName());
    //throw bad request
        if(exists) throw new NotFoundException("404", "Category exists");
}
}
