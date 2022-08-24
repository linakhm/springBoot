package com.example.demoLina.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demoLina.Model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;

public interface ProductRepo extends PagingAndSortingRepository<Product,Integer> {



}
