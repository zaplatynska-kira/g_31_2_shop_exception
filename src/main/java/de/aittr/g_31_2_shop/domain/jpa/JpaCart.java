package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "cart")

public class JpaCart implements Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @ManyToMany
    @JoinTable(
            name = "cart_product" ,
                    joinColumns =@JoinColumn(name ="cart_id"),
            inverseJoinColumns = @JoinColumn(name ="product_id")

    )
    private List<JpaProduct> products=new ArrayList<>();
@OneToOne
@JoinColumn(name = "customer_id")
    private JpaCustomer customer;


    public JpaCart() {
    }

    public JpaCart(int id, List<JpaProduct> products) {
        this.id = id;
        this.products = products;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public List<Product> getProducts() {
        //  посмотреть как на практике и потом переделать

        return new ArrayList<>(products);
    }

    @Override
    public void addProduct(Product product) {
        try {
            products.add((JpaProduct)product);
        }catch (Exception e){
            throw  new IllegalArgumentException(" в корзину JpaCart передан неизвестный тип продукта");
        }
    }
// проверить на практике и переделать
    @Override
    public void deleteProductById(int productId) {
products.removeIf(p->p.getId()==productId);
    }

    @Override
    public void clear() {
products.clear();
    }

    @Override
    public double getTotalPrice() {
        return products
                .stream()
                .filter(x->x.isActive())
                .mapToDouble(p->p.getPrice())
                .sum();
    }

    @Override
    public double getAveragePrice() {
        return products
                .stream()
                .filter(x->x.isActive())
                .mapToDouble(p->p.getPrice())
                .average()
                .orElse(0);
    }

    public void setProducts(List<JpaProduct> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(JpaCustomer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaCart jpaCart)) return false;
        return getId() == jpaCart.getId() && Objects.equals(getProducts(), jpaCart.getProducts()) && Objects.equals(getCustomer(), jpaCart.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProducts(), getCustomer());
    }

    @Override
    public String toString() {
        return "JpaCart{" +
                "id=" + id +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }
}
