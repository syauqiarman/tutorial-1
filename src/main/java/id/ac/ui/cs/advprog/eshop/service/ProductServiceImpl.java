package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create (Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> producIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        producIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findId(String productId) {
        return productRepository.findId(productId);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public Product deleteProduct(Product product){
        productRepository.deleteProduct(product.getProductId());
        return product;
    }
}