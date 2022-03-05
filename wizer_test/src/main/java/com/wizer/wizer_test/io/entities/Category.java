package com.wizer.wizer_test.io.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue
    private long id;
    private String categoryName;
}
