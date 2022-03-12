package ru.gb.gbthymeleafwinter.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleafwinter.dao.CartDao;
import ru.gb.gbthymeleafwinter.entity.Cart;
import ru.gb.gbthymeleafwinter.entity.Product;

import java.awt.desktop.OpenFilesEvent;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class CartService {

    private final CartDao cartDao;

//    private final Cart cart;



    @Transactional
    public Product addProduct(Product product) {
        Cart cart;
        Optional<Cart> cartFromDbOptional = cartDao.findById(1L);
        if(cartFromDbOptional.isPresent()) {
            cart = cartFromDbOptional.get();
            Set<Product> products = cart.getProducts();
            products.add(product);
            cart.setProducts(products);
        } else {
            cart = new Cart();
            cart.setProducts(Set.of(product));
        }
        cartDao.save(cart);
        return product;
    }

   @Transactional
    public void deleteProduct(Product product) {
        Optional<Cart> cartFromDBOptional = cartDao.findById(1L);
        if(cartFromDBOptional.isPresent()) {
            Cart cart = cartFromDBOptional.get();
            Set<Product> products = cart.getProducts();
            products.remove(product);
            cartDao.save(cart);
        }
    }


    @Transactional(readOnly = true)
    public Set<Product> getProducts() {
        Optional<Cart> cartFromDbOptional = cartDao.findById(1L);
        if(cartFromDbOptional.isPresent()) {
            Cart cart = cartFromDbOptional.get();
            return cart.getProducts();
        }
        return new Cart().getProducts();
    }

}
