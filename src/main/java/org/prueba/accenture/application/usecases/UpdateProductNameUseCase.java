package org.prueba.accenture.application.usecases;

import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductNameUseCase {

    private final ProductRepository productRepository;

    public UpdateProductNameUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product execute(Long productId, String newName) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Producto no encontrado con ID: " + productId);
        }
        Product product = optionalProduct.get();
        product.setName(newName);

        return productRepository.save(product);
    }
}
