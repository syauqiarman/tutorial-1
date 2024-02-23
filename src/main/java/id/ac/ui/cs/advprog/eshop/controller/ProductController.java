package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
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

@Controller
@RequestMapping("/car")
class CarController extends ProductController{
    @Autowired
    private CarServiceImpl carservice;
    
    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model){
        carservice.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model){
        List<Car> allCars = carservice.findAll();
        model.addAttribute("cars", allCars) ;
        return "carList";
    }

    @GetMapping("/editCar/carId}")
    public String editCarPage(@PathVariable String carId, Model model){
        Car car = carservice.findById(carId);
        model. addAttribute("car", car);
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model){
        System.out.println(car.getCarId());
        carservice. update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carservice.deleteCarById(carId);
        return "redirect:listCar";
    }
}