package de.aittr.g_31_2_shop.domain.dto;

import de.aittr.g_31_2_shop.domain.jpa.JpaCustomer;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartDto {


    private int id;

    private List<ProductDto> products=new ArrayList<>();

    public CartDto(int id, List<ProductDto> products) {
        this.id = id;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartDto cartDto)) return false;
        return getId() == cartDto.getId() && Objects.equals(getProducts(), cartDto.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProducts());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
