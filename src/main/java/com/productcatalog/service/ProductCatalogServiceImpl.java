package com.productcatalog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcatalog.bean.Product;
import com.productcatalog.persistence.ProductCatalogDao;

@Service
public class ProductCatalogServiceImpl implements ProductCatalogService {
	@Autowired
	private ProductCatalogDao productCatalogDao;

	@Override
	public List<Product> getAllProducts() {
		return productCatalogDao.findAll();
	}

	@Override
	public Optional<Product> getProductByCode(String productCode) {
		return productCatalogDao.findByCode(productCode);
	}
}