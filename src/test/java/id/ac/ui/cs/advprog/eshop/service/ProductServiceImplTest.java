package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Captor
    ArgumentCaptor<Product> productCaptor;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Bola");
        product.setProductQuantity(10);
        when(productRepository.create(product)).thenReturn(product);
        
        Product createdProduct = productService.create(product);
        assertEquals(product.getProductName(), createdProduct.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductName("Bola");
        product1.setProductQuantity(10);
        productList.add(product1);

        Product product2 = new Product();
        product2.setProductName("Sepatu");
        product2.setProductQuantity(50);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList.iterator());
        
        List<Product> allProducts = productService.findAll();
        
        assertEquals(productList.size(), allProducts.size());
        assertTrue(allProducts.contains(product1) && allProducts.contains(product2));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Sendal");
        product.setProductQuantity(10);
        when(productRepository.findId(product.getProductId())).thenReturn(product);

        Product foundProduct = productService.findId(product.getProductId());

        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findId(product.getProductId());
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Bola");
        product.setProductQuantity(10);

        productService.update(product);

        verify(productRepository, times(1)).update(productCaptor.capture());
        Product capturedProduct = productCaptor.getValue();
        assertEquals(product.getProductId(), capturedProduct.getProductId());
        assertEquals(product.getProductName(), capturedProduct.getProductName());
        assertEquals(product.getProductQuantity(), capturedProduct.getProductQuantity());
    }

    @Test
    void testDelete() {
        Product productToDelete = new Product();
        productToDelete.setProductId("123");
        productToDelete.setProductName("Sepatu");
        productToDelete.setProductQuantity(50);
        productRepository.create(productToDelete);

        when(productRepository.deleteProduct(productToDelete.getProductId())).thenReturn(productToDelete);

        Product deletedProduct = productService.deleteProduct(productToDelete); 
        
        verify(productRepository, times(1)).deleteProduct(productToDelete.getProductId());

        assertEquals("Sepatu", productToDelete.getProductName());
        assertEquals(50, productToDelete.getProductQuantity());
        assertEquals("123", deletedProduct.getProductId());
    }
}