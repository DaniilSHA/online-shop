package com.example.productmicroservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories")
@Proxy(lazy = false)
public class Category {
    private static final String SEQ_NAME = "category_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    public Category(long id) {
        this.id = id;
    }
}
