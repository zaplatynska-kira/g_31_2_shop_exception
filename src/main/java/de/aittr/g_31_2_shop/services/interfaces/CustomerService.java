package de.aittr.g_31_2_shop.services.interfaces;

import de.aittr.g_31_2_shop.domain.dto.CustomerDto;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerDto customer);
    List<CustomerDto> getAllCustomer();

    List<CustomerDto> getAllActiveCustomers();

    CustomerDto getActiveCustomerById(int id);
    void update(Customer customer);
    void delete(String name);

    void deleteById(int id);

    void deleteByName(String name);

    void restoreById(int id);
    int getActiveCustomerCount();

    int getActiveCustomersCount();

    double getCartTotalPriceById(int customerId);
    double getAverageProductPriceById(int customerId);
    void addProductToCart(int customerId, int productId);
    void deleteProductFromCart(int customerId, int productId);
    void clearCartById(int customerId);
}
