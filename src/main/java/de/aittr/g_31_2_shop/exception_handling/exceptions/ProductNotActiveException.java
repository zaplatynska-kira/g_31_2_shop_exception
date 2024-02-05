package de.aittr.g_31_2_shop.exception_handling.exceptions;

public class ProductNotActiveException extends RuntimeException {
    public ProductNotActiveException(String message) {
        super(message);
    }
}

