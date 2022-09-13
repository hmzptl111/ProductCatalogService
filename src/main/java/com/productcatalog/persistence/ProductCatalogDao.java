package com.productcatalog.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productcatalog.bean.Product;

@Repository
public interface ProductCatalogDao extends JpaRepository<Product, Long> {
	List<Product> findAll();
	
	Optional<Product> findByCode(String productCode);
}