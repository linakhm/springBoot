package com.example.demoLina.Service;
import com.example.demoLina.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demoLina.Repository.ProductRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Pageable;
@Service
public class ProductService {
    @Autowired
    private ProductRepo repository;
    @Autowired
    private ProductRepo repo;

    public void saveProduct(Product product) {
        this.repo.save(product);
    }
    public Product getProductById(Integer id) {
        Optional < Product > optional = repo.findById(id);
        Product prod = null;
        if (optional.isPresent()) {
            prod = optional.get();
        } else {
            throw new RuntimeException(" Product not found for id :: " + id);
        }
        return prod;
    }

    public Page<Product> listAll(int pageNum) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return repo.findAll(pageable);
    }
    public void deleteProductById(Integer id) {
        this.repo.deleteById(id);
    }
    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repo.findAll(pageable);
    }
  }




