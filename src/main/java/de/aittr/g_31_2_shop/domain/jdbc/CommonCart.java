package de.aittr.g_31_2_shop.domain.jdbc;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommonCart implements Cart {

    private int id;
    private List<Product>products = new ArrayList<>();



    public CommonCart() {
    }

    public CommonCart(int id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public CommonCart(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
products.add(product);
    }

    @Override
    public void deleteProductById(int productId) {
products.removeIf(p-> p.getId()==productId) ;
    }

    @Override
    public void clear() {
products.clear();
    }

    @Override
    public double getTotalPrice() {

        return products.stream()
                .filter(p->p.isActive())
                .mapToDouble(p->p.getPrice())
                .sum();
    }

    @Override
    public double getAveragePrice() {
        return products.stream()
                .filter(p->p.isActive())
                .mapToDouble(p->p.getPrice())
                .average()
                .orElse(0); //  возвращает результ если он есть или то что мы передали 0
                //average  среднее стоимость
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommonCart that)) return false;
        return getId() == that.getId() && Objects.equals(getProducts(), that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProducts());
    }

    @Override
    public String toString() {
       return products.toString();
    }
}
