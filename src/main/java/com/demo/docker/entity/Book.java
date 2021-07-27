package com.demo.docker.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "title", nullable = false)
    String title;

    @ManyToMany(mappedBy = "books")
    Set<CustomerOrder> customerOrders;
}
