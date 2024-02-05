package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
@Table(name = "customer")
public class JpaCustomer implements Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
    private int id;

    @Column(name = "is_active")
    private boolean  isActive;
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    @Min(18)
    private int age;

    @Column(name = "email")
    @Email
    private String email;



@OneToOne(mappedBy = "customer")
private JpaCart cart;

    public JpaCustomer(int id, boolean b, String name, JpaCart cart) {
    }

    public JpaCustomer(int id, boolean isActive, String name, int age, String email, JpaCart cart) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.age = age;
        this.email = email;
        this.cart = cart;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void setCart(Cart cart) {;
try {
    this.cart = (JpaCart) cart;
}catch (Exception e){
    throw  new IllegalArgumentException(" в стетер передан неизвестный тип корзины");
}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaCustomer that)) return false;
        return getId() == that.getId() && isActive() == that.isActive() && Objects.equals(getName(), that.getName()) && Objects.equals(getCart(), that.getCart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getName(), getCart());
    }

    @Override
    public String toString() {
        return "JpaCustomer{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                '}';
    }
}
