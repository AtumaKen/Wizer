package com.wizer.wizer_test.io.repositories;

import com.wizer.wizer_test.io.entities.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
