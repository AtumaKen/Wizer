package com.wizer.wizer_test.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    @NotBlank(message = "Book name is required")
    private String bookName;
    @NotBlank(message = "Author is required")
    private String author;
    @NotNull(message = "categoryId is required")
    private Long categoryId;
    @NotNull(message = "isFavorite is required")
    private Boolean isFavorite;
}
