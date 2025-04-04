package org.prueba.accenture.application.usecases;

import org.prueba.accenture.infrastructure.persistence.ProductEntity;
import org.prueba.accenture.infrastructure.persistence.JpaProductRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductStockUseCase {

    private final JpaProductRepository productRepository;

    public UpdateProductStockUseCase(JpaProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void execute(Long productId, int newStock) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        product.setStock(newStock);
        if (product.getBranch() == null) {
            throw new RuntimeException("El producto no tiene una sucursal asignada");
        }

        productRepository.save(product);
    }
}
