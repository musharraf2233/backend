package com.youtube.ecommerce.dao;

import com.youtube.ecommerce.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
    public List<Product> findAll(Pageable pageable);
    
    public List<Product> findAll();
    
 
    public List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
            String key1, String key2, Pageable pageable
    );
    
//    public List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCaseAndType(String key1, String key2,String type, Pageable pageable);
    

    List<Product> findByType(Pageable pageable,String type);
    
    List<Product> findByBestSeller(Boolean bestSeller);
    
    List<Product> findByTrendProduct(Boolean trendProduct);

	List<Product> findByProductName(String productName);
    
}
