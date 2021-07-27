package com.demo.docker.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "custumerOrder")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "customerId"), nullable = false)
    Customer customer;

    @ManyToMany
    @JoinTable(name = "customerOrderBook",
        joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "customerOrderId")),
        inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "bookId")))
    Set<Book> books;
}
