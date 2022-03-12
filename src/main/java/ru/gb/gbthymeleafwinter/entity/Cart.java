package ru.gb.gbthymeleafwinter.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleafwinter.entity.security.AccountRole;
import ru.gb.gbthymeleafwinter.entity.security.Authority;

import javax.persistence.*;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
@Entity
@Scope("prototype")
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status = "not empty";


//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "cart_product",
 //   joinColumns = @JoinColumn(name = "cart_id"),
 //   inverseJoinColumns = @JoinColumn(name = "product_id"))
        joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "ID")})
    private Set<Product> products;


    public Cart(Product product) {
        products.add(product);
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public boolean deleteProduct(Product product) {
        return products.remove(product);
    }


}


