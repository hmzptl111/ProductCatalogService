package com.productcatalog.service;

import java.util.List;
import java.util.Optional;

import com.productcatalog.bean.Product;

public interface ProductCatalogService {
	List<Product> getAllProducts();
	
	Optional<Product> getProductByCode(String productCode);
}