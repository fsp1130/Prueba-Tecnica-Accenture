package org.prueba.accenture.infrastructure.rest;

import org.prueba.accenture.application.usecases.GetTopProductPerBranchUseCase;
import org.prueba.accenture.application.usecases.GetTopStockedProductsUseCase;
import org.prueba.accenture.application.usecases.UpdateProductNameUseCase;
import org.prueba.accenture.application.usecases.UpdateProductStockUseCase;
import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.infrastructure.mapper.ProductMapper;
import org.prueba.accenture.infrastructure.persistence.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final GetTopStockedProductsUseCase getTopStockedProductsUseCase;
    private final UpdateProductNameUseCase updateProductNameUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;
    private final GetTopProductPerBranchUseCase getTopProductPerBranchUseCase;

    @Autowired
    public ProductoController(GetTopStockedProductsUseCase getTopStockedProductsUseCase,
                              UpdateProductNameUseCase updateProductNameUseCase,
                              UpdateProductStockUseCase updateProductStockUseCase,
                              GetTopProductPerBranchUseCase getTopProductPerBranchUseCase) {
        this.getTopStockedProductsUseCase = getTopStockedProductsUseCase;
        this.updateProductNameUseCase = updateProductNameUseCase;
        this.updateProductStockUseCase = updateProductStockUseCase;
        this.getTopProductPerBranchUseCase = getTopProductPerBranchUseCase;
    }

    @GetMapping("/top-stock/{franquiciaId}")
    public ResponseEntity<List<ProductEntity>> obtenerProductosConMasStock(@PathVariable Long franquiciaId) {
        List<Product> productos = getTopStockedProductsUseCase.execute(franquiciaId);
        List<ProductEntity> entidades = productos.stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(entidades);
    }

    @PutMapping("/{id}/nombre")
    public ResponseEntity<Product> actualizarNombreProducto(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String newName = body.get("newName");
        Product updated = updateProductNameUseCase.execute(id, newName);
        return ResponseEntity.ok(updated);
    }


    @PutMapping("/{id}/stock")
    public ResponseEntity<Void> actualizarStockProducto(
            @PathVariable Long id,
            @RequestParam int nuevoStock
    ) {
        updateProductStockUseCase.execute(id, nuevoStock);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/top-por-sucursal/{franquiciaId}")
    public ResponseEntity<List<ProductEntity>> obtenerTopProductosPorSucursal(
            @PathVariable Long franquiciaId
    ) {
        List<ProductEntity> productos = getTopProductPerBranchUseCase.execute(franquiciaId);
        return ResponseEntity.ok(productos);
    }

}
