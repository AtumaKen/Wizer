package com.wizer.wizer_test.service;

import com.wizer.wizer_test.exceptions.NotFoundException;
import com.wizer.wizer_test.helper.Validations;
import com.wizer.wizer_test.io.entities.Book;
import com.wizer.wizer_test.io.entities.Category;
import com.wizer.wizer_test.io.repositories.BookRepository;
import com.wizer.wizer_test.io.repositories.CategoryRepository;
import com.wizer.wizer_test.models.request.BookRequest;
import com.wizer.wizer_test.models.response.BookResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Validations validations;

    public BookResponse createBook(BookRequest request){
        validations.validateBook(request);
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("404", "Category does not exist"));

        Book mapped = mapper.map(request, Book.class);
        Book savedBook = bookRepository.save(mapped);
        BookResponse response = mapper.map(savedBook, BookResponse.class);
        response.setCategory(category);
        return response;
    }

    public BookResponse updateBook(long id, BookRequest request) {
        validations.validateBook(request);

        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("404", "Book does not exist"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("404", "Category does not exist"));
        mapper.map(request, book);
        Book savedBook = bookRepository.save(book);
        BookResponse response = mapper.map(savedBook, BookResponse.class);
        response.setCategory(category);
        return  response;
    }

    public List<BookResponse> getAll(int page, int size) {
        Page<Book> books = bookRepository.findAll(PageRequest.of(page, size));
        List<BookResponse> responseList = new ArrayList<>();
         books.forEach(book -> {
             BookResponse response = mapper.map(book, BookResponse.class);
             Optional<Category> byId = categoryRepository.findById(book.getCategoryId());
             byId.ifPresent(response::setCategory);
             responseList.add(response);
         });
         return responseList;
    }

    public List<BookResponse> getFavorites() {
        List<Book> byIsFavorite = bookRepository.findByIsFavorite(true);
        List<BookResponse> responseList = new ArrayList<>();
        byIsFavorite.forEach(book -> {
            BookResponse response = mapper.map(book, BookResponse.class);
            Optional<Category> byId = categoryRepository.findById(book.getCategoryId());
            byId.ifPresent(response::setCategory);
            responseList.add(response);
        });
        return responseList;
    }
}
