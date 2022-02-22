package ru.gb.gbthymeleafwinter.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleafwinter.dao.CartDao;
import ru.gb.gbthymeleafwinter.entity.Cart;
import ru.gb.gbthymeleafwinter.entity.Product;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class CartService {


    private final CartDao cartDao;


   private final Cart cart = new Cart();



    @Transactional
    public Product addProduct(Product product) {
        cart.addProduct(product);
        cartDao.save(cart);
        return product;
    }

   @Transactional
    public void deleteProduct(Product product) {
        cart.deleteProduct(product);
        cartDao.save(cart);
    }


    @Transactional(readOnly = true)
    public Set<Product> getProducts() {
        return cart.getProducts();
    }

}
