package com.example.glovo.repository;

import com.example.glovo.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findAllByOrderId(int id);
}
