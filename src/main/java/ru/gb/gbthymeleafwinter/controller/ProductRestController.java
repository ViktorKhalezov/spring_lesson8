package ru.gb.gbthymeleafwinter.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbthymeleafwinter.entity.Product;
import ru.gb.gbthymeleafwinter.service.CartService;
import ru.gb.gbthymeleafwinter.service.ProductService;
import java.net.URI;
import java.util.List;
import java.util.Set;

//@RestController
//@AllArgsConstructor
//@RequestMapping("api/v1/product")
//public class ProductRestController {
//
//    private final ProductService productService;
//
//    private final CartService cartService;
//
//
//
//    @GetMapping("/all")
//    public List<Product> getProductList() {
//        return productService.findAll();
//    }
//
//    @GetMapping("{productId}")
//    public ResponseEntity<? extends Product> getProduct(@PathVariable("productId") Long id) {
//        if(id != null) {
//            Product product = productService.findById(id);
//            if(product != null) {
//                return new ResponseEntity<>(product, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createProduct(@RequestBody Product product) {
//        Product savedProduct = productService.save(product);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(URI.create("/api/v1/product/" + savedProduct.getId()));
//        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
//    }
//
//    @PutMapping("{productId}")
//    public ResponseEntity<?> updateProduct(@PathVariable("productId") Long id, @RequestBody Product product) {
//        product.setId(id);
//        productService.save(product);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/{productId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteById(@PathVariable("productId") Long id) {
//        productService.deleteById(id);
//    }
//
//
//    @GetMapping("/cart")
//    public Set<Product> showCart() {
//        return cartService.getProducts();
//    }
//
//    @PutMapping("/addToCart" + "/{productId}")
//    public ResponseEntity<?> addProductToCart(@PathVariable("productId") Long id) {
//        Product product = productService.findById(id);
//        cartService.addProduct(product);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/deleteFromCart" + "/{productId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteProductFromCart(@PathVariable("productId") Long id) {
//        cartService.deleteProduct(productService.findById(id));
//    }
//
//
//}
