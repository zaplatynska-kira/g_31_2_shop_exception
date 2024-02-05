package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.exception_handling.exceptions.FourTestException;
import de.aittr.g_31_2_shop.exception_handling.exceptions.ThirdTestException;
import de.aittr.g_31_2_shop.repositories.jpa.JpaProductRepository;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaProductService implements ProductService {

    private JpaProductRepository repository;
    private ProductMappingService mappingService;

    public JpaProductService(JpaProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        try {
            JpaProduct entity = mappingService.mapDtoToJpaProduct(dto);
            entity.setID(0);
            entity = repository.save(entity);
            return mappingService.mapProductEntityToDto(entity);

        } catch (Exception e){
            throw new FourTestException(e.getMessage());
        }

    }


    @Override
    public List<ProductDto> getAllActiveProducts() {

        return repository.findAll()
                .stream()
                .filter(p-> p.isActive())
                .map(p->mappingService.mapProductEntityToDto(p))
                .toList();
    }


    @Override
    public ProductDto getActiveProductById(int id) {
Product product = repository.findById(id).orElse(null);

if(product!= null&& product.isActive()){

    return mappingService.mapProductEntityToDto(product);
}
        throw new ThirdTestException("Продукт с указанным идентификатором отсутствует в базе данных");
    }

    @Override
    public void update(ProductDto dto) {
        JpaProduct entity = mappingService.mapDtoToJpaProduct(dto);
repository.save(entity);
    }

    //@Override
    //public void deleteById(int id) {
       // repository.deleteById(id);

   // }
   @Override
   @Transactional
    public void deleteById(int id){
        Product product= repository.findById(id).orElse(null);
        if ((product != null && product.isActive())) {
            product.setActive(false);
        }
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Product product= repository.findByName(name);
        if (product != null && product.isActive()) {
            product.setActive(false);
        }
    }

    @Override
    @Transactional
    public void restoreById(int id) {
        Product product= repository.findById(id).orElse(null);
        if ((product != null && !product.isActive())) {
            product.setActive(true);
        }
    }


    @Override
    public int getActiveProductCount() {
        return repository.countByIsActiveTrue();
    }

    @Override
    public double getActiveProductTotalPrice() {
        return 0;
    }

    @Override
    public double getActiveProductsTotalPrice() {
        List<JpaProduct> activeProducts = repository.findByIsActiveTrue();
        return activeProducts.stream()
                .mapToDouble(JpaProduct::getPrice)
                .sum();
    }

    @Override
    public double getActiveProductAveragePrice() {
        List<JpaProduct> activeProducts = repository.findByIsActiveTrue();
        return activeProducts.stream()
                .mapToDouble(JpaProduct::getPrice)
                .average()
                .orElse(0.0);
    }
}
