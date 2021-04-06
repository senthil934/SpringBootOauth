package com.pack.SpringBootSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.SpringBootSecurity.model.Product;



public interface ProductRepsitory extends JpaRepository<Product, Long> {

}
