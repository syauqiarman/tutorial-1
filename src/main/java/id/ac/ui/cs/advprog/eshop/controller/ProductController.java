package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService service;
    private static final String REDIRECT_LIST = "redirect:list";

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }
    
    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return REDIRECT_LIST;
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll(); 
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping(value = "/edit/{productId}")
    public String editProductPage(@PathVariable("productId") String productId, Model model) {
        Product product = service.findId(productId);
        model.addAttribute("product", product);
        return "EditProduct"; 
    }

    @PostMapping("/update")
    public String editProductPost(@ModelAttribute Product product, Model model) {
        service.update(product);
        return REDIRECT_LIST; 
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable ("productId") String productId, Model model){
        service.deleteProduct(service.findId(productId));
        return "redirect:../list";
    }
}