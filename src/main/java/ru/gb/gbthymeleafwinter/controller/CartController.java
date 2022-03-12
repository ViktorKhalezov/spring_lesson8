package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbthymeleafwinter.service.CartService;
import ru.gb.gbthymeleafwinter.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;


    @GetMapping
    @PreAuthorize("hasAuthority('cart.read')")
    public String showCart(Model model) {
        model.addAttribute("products", cartService.getProducts());
        return "cart";
    }

    @GetMapping("/addToCart")
    @PreAuthorize("hasAuthority('cart.add_product')")
    public String addProductToCart(@RequestParam(name = "id") Long id){
        cartService.addProduct(productService.findById(id));
        return "redirect:/product/all";
    }

    @GetMapping("/deleteFromCart")
    @PreAuthorize("hasAuthority('cart.delete_product')")
    public String deleteProductFromCart(@RequestParam(name = "id") Long id) {
        cartService.deleteProduct(productService.findById(id));
        return "redirect:/cart";
    }

}
