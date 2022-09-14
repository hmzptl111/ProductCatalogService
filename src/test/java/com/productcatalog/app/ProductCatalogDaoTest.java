package com.productcatalog.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.productcatalog.bean.Product;
import com.productcatalog.persistence.ProductCatalogDao;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductCatalogDaoTest {
	@Autowired
	private ProductCatalogDao productCatalogDao;
	
	private List<Product> products;

	@BeforeEach
	void setUp() throws Exception {
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
		products.clear();
	}

	@Test
	void testFindAll() {
		assertEquals(products.size(), productCatalogDao.findAll().size());
	}
	
	@Test
	void testFindByCodeOne() {
		String productCode = "A003";
		assertTrue(productCatalogDao.findByCode(productCode).isPresent());
	}
	
	@Test
	void testFindByCodeTwo() {
		String productCode = "A006";
		assertFalse(productCatalogDao.findByCode(productCode).isPresent());
	}
}