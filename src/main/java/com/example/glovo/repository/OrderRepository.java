package com.example.glovo.repository;

import com.example.glovo.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository <Order, Integer> {

    List<Order> findAll();

}
