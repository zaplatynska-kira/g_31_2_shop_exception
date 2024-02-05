package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
@Table(name= "product")
public class JpaProduct implements Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Poteto-ok
    //poteto-x
    //POTETO-x
    //Po-x
    //Poteto7-x
    @Column(name = "name")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z][a-z][3,]")

    private String name;
    @Column(name = "price")
    @Min(10)
    @Max(10000)
    private double price;
    @Column(name = "isActive")
    private boolean isActive;

    public JpaProduct() {
    }

    public JpaProduct(int id, String name, double price, boolean isActive) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isActive = isActive;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaProduct that)) return false;
        return getId() == that.getId() && Double.compare(that.getPrice(), getPrice()) == 0 && isActive() == that.isActive() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), isActive());
    }

    @Override
    public String toString() {
        return "JpaProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isActive=" + isActive +
                '}';
    }
}

