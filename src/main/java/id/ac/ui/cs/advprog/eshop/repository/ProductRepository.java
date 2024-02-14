package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    
    public Product create(Product product) {
        product.setProductId(String.valueOf(UUID.randomUUID()));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findId(String productId) {
        for (Product thisProduct : productData) {
            if (thisProduct.getProductId().equals(productId)) {
                return thisProduct;
            }
        }
        return null;
    }

    public Product update(Product product) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(product.getProductId())) {
                productData.set(i, product);
            }
        }
        return product;
    }

    public Product deleteProduct(String id) throws NullPointerException{
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                productData.remove(product);
                return product;
            }
        }
        throw new NullPointerException("Product not found");
    }
}