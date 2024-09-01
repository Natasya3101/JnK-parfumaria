package com.parfumaria.be.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parfumaria.be.models.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer>{
    Optional<Products> findByName(String name);
    Products findProductsById(Integer id);
}
