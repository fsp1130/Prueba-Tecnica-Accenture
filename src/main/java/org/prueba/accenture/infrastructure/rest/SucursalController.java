package org.prueba.accenture.infrastructure.rest;

import org.prueba.accenture.application.usecases.AddProductToBranchUseCase;
import org.prueba.accenture.application.usecases.RemoveProductFromBranchUseCase;
import org.prueba.accenture.application.usecases.UpdateBranchNameUseCase;
import org.prueba.accenture.application.usecases.UpdateProductStockUseCase;
import org.prueba.accenture.domain.model.Branch;
import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.domain.repository.BranchRepository;
import org.prueba.accenture.infrastructure.mapper.ProductMapper;
import org.prueba.accenture.infrastructure.persistence.BranchEntity;
import org.prueba.accenture.infrastructure.persistence.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final AddProductToBranchUseCase addProductToBranchUseCase;
    private final RemoveProductFromBranchUseCase removeProductFromBranchUseCase;
    private final UpdateBranchNameUseCase updateBranchNameUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;
    private final ProductMapper productMapper;
    private final BranchRepository branchRepository;


    @Autowired
    public SucursalController(AddProductToBranchUseCase addProductToBranchUseCase,
                              RemoveProductFromBranchUseCase removeProductFromBranchUseCase,
                              UpdateProductStockUseCase updateProductStockUseCase,
                              ProductMapper productMapper,
                              BranchRepository branchRepository,
                              UpdateBranchNameUseCase updateBranchNameUseCase) {
        this.addProductToBranchUseCase = addProductToBranchUseCase;
        this.removeProductFromBranchUseCase = removeProductFromBranchUseCase;
        this.updateProductStockUseCase = updateProductStockUseCase;
        this.productMapper = productMapper;
        this.branchRepository = branchRepository;
        this.updateBranchNameUseCase = updateBranchNameUseCase;
    }

    @PostMapping("/{id}/productos")
    public ResponseEntity<Branch> agregarProducto(@PathVariable Long id, @RequestBody Product product) {
        System.out.println("ID recibido en el controller: " + id);
        return ResponseEntity.ok(addProductToBranchUseCase.execute(id, product));
    }


    @DeleteMapping("/{sucursalId}/productos/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long sucursalId, @PathVariable Long productoId) {
        removeProductFromBranchUseCase.execute(sucursalId, productoId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/nombre")
    public ResponseEntity<Branch> actualizarNombreSucursal(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String newName = body.get("newName");
        Branch updated = updateBranchNameUseCase.execute(id, newName);
        return ResponseEntity.ok(updated);
    }

   /* @PutMapping("/{sucursalId}/productos/{productoId}/stock")
    public ResponseEntity<ProductEntity> actualizarStock(@PathVariable Long sucursalId, @PathVariable Long productoId, @RequestParam int stock) {
        Product product = updateProductStockUseCase.execute(sucursalId, productoId, stock);

        // Obtener la BranchEntity desde el repositorio
        Optional<BranchEntity> branchEntityOpt = branchRepository.findById(sucursalId);
        if (!branchEntityOpt.isPresent()) {
            throw new RuntimeException("Sucursal no encontrada");
        }
        BranchEntity branchEntity = branchEntityOpt.get();

        // Convertir Product a ProductEntity usando el mapper
        ProductEntity productEntity = productMapper.toEntity(product, branchEntity);

        return ResponseEntity.ok(productEntity);
    }*/

}