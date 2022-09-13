package com.productcatalog.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalog.bean.Product;
import com.productcatalog.service.ProductCatalogService;

@RestController
@RequestMapping("/products")
public class ProductCatalogResource {

	@Autowired
	private ProductCatalogService productCatalogService;
	
	@GetMapping(path = "")
	public List<Product> listAllProducts() {
		return productCatalogService.getAllProducts();
	}
	
	@GetMapping(path = "/code/{productCode}")
	public Optional<Product> getProductByCode(@PathVariable("productCode") String productCode) {
		return productCatalogService.getProductByCode(productCode);
	}
}
