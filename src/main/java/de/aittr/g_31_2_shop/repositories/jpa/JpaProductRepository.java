package de.aittr.g_31_2_shop.repositories.jpa;

import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<JpaProduct,Integer> {
    JpaProduct findByName(String name);

    List<JpaProduct> findByIsActiveTrue();

    int countByIsActiveTrue();


    JpaProduct findByNameAndIsActiveTrue(String name);
}

