package com.wizer.wizer_test.io.repositories;

import com.wizer.wizer_test.io.entities.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    List<Book> findByIsFavorite(boolean b);
    Boolean existsByBookNameAndAuthorAndCategoryId(String name, String author, Long categoryId);
}
