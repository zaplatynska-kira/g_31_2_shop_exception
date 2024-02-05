package de.aittr.g_31_2_shop.domain.interfaces;

public interface Product {

     int getId();

     boolean isActive();

     String getName();

     double getPrice();

     void setID(int id);

     void setActive(boolean isActive);
}