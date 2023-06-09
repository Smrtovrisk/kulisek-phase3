package com.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import com.bean.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("SELECT p FROM Product p JOIN FETCH p.listOfOrders o")
    List<Product> findAllProductsWithUsers();
}

