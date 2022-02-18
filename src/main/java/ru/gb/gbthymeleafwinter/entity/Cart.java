package ru.gb.gbthymeleafwinter.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status = "not empty";

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "cart_product",
    joinColumns = @JoinColumn(name = "cart_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();


    public Cart(Product product) {
        products.add(product);
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public boolean deleteProduct(Product product) {
        return products.remove(product);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id.equals(cart.id) && status.equals(cart.status) && products.equals(cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, products);
    }
}
