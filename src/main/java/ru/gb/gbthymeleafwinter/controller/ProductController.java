package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbthymeleafwinter.entity.Product;
import ru.gb.gbthymeleafwinter.service.CartService;
import ru.gb.gbthymeleafwinter.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final CartService cartService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('product.read')")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAuthority('product.read')")
    public String info(Model model, @PathVariable(name = "productId") Long id) {
        Product product;
        if(id != null) {
            product = productService.findById(id);
        } else {
            return "redirect:/product/all";
        }
        model.addAttribute("product", product);
        return "product-info";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create, product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        Product product;

        if (id != null) {
            product = productService.findById(id);
        } else {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create, product.update')")
    public String saveProduct(Product product) {
        productService.save(product);
        return "redirect:/product/all";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('product.delete')")
    public String deleteById(@RequestParam(name = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("products", cartService.getProducts());
        return "cart";
    }

    @GetMapping("/addToCart")
    public String addProductToCart(@RequestParam(name = "id") Long id){
        cartService.addProduct(productService.findById(id));
        return "redirect:/product/all";
    }

    @GetMapping("/deleteFromCart")
    public String deleteProductFromCart(@RequestParam(name = "id") Long id) {
        cartService.deleteProduct(productService.findById(id));
        return "redirect:/product/cart";
    }


}
