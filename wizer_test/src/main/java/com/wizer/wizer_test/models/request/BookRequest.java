package com.wizer.wizer_test.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private String bookName;
    private String author;
    private CategoryRequest category;
}
