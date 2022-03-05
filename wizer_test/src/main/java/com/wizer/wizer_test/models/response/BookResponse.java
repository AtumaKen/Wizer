package com.wizer.wizer_test.models.response;

import com.wizer.wizer_test.io.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookResponse {
    private long id;
    private String bookName;
    private String author;
    private Category category;
    private boolean isFavorite;
}
