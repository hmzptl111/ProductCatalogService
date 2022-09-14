package com.productcatalog.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.productcatalog.bean.Product;
import com.productcatalog.persistence.ProductCatalogDao;
import com.productcatalog.service.ProductCatalogServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductCatalogServiceTest {
	@InjectMocks
	@Autowired
	private ProductCatalogServiceImpl productCatalogServiceImpl;
	
	@Mock
	private ProductCatalogDao productCatalogDao;
	
	private AutoCloseable autoCloseable;
	
	private List<Product> products;
	
	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
		
		Product p1 = new Product(1, "A001", "ABC", "This is ABC", 100);
		Product p2 = new Product(2, "A002", "DEF", "This is DEF", 200);
		Product p3 = new Product(3, "A003", "GHI", "This is GHI", 300);
		Product p4 = new Product(4, "A004", "JKL", "This is JKL", 400);
		Product p5 = new Product(5, "A005", "MNO", "This is MNO", 500);
		
		products = new ArrayList<>();
		products.addAll(Arrays.asList(p1, p2, p3, p4, p5));
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
		products.clear();
	}

	@Test
	void testGetAllProductsOne() {
		Mockito.when(productCatalogDao.findAll()).thenReturn(products);
		
		assertEquals(products, productCatalogServiceImpl.getAllProducts());
	}
	
	@Test
	void testGetAllProductsTwo() {
		products.clear();
		
		Mockito.when(productCatalogDao.findAll()).thenReturn(new ArrayList<>());
		
		assertTrue(productCatalogServiceImpl.getAllProducts().size() == 0);
	}

	@Test
	void testGetProductByCodeOne() {
		String productCode = "A003";
		Product product = products.stream().filter(p -> p.getCode().equals(productCode)).collect(Collectors.toList()).get(0);
		Mockito.when(productCatalogDao.findByCode(productCode)).thenReturn(Optional.ofNullable(product));
	
		assertEquals(product, productCatalogServiceImpl.getProductByCode(productCode).get());
	}
	
	@Test
	void testGetProductByCodeTwo() {
		String productCode = "A006";
		Product product = null;
		Mockito.when(productCatalogDao.findByCode(productCode)).thenReturn(Optional.ofNullable(product));
		
		assertFalse(productCatalogServiceImpl.getProductByCode(productCode).isPresent());
	}
}