package org.prueba.accenture.infrastructure.rest;

import org.prueba.accenture.application.usecases.AddBranchToFranchiseUseCase;
import org.prueba.accenture.application.usecases.CreateFranchiseUseCase;
import org.prueba.accenture.application.usecases.UpdateFranchiseNameUseCase;
import org.prueba.accenture.domain.model.Branch;
import org.prueba.accenture.domain.model.Franchise;
import org.prueba.accenture.infrastructure.persistence.BranchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    private final CreateFranchiseUseCase createFranchiseUseCase;
    private final AddBranchToFranchiseUseCase addBranchToFranchiseUseCase;
    private final UpdateFranchiseNameUseCase updateFranchiseNameUseCase;

    @Autowired
    public FranquiciaController(CreateFranchiseUseCase createFranchiseUseCase,
                                AddBranchToFranchiseUseCase addBranchToFranchiseUseCase,
                                UpdateFranchiseNameUseCase updateFranchiseNameUseCase) {
        this.createFranchiseUseCase = createFranchiseUseCase;
        this.addBranchToFranchiseUseCase = addBranchToFranchiseUseCase;
        this.updateFranchiseNameUseCase = updateFranchiseNameUseCase;
    }

    @PostMapping
    public ResponseEntity<Franchise> crearFranquicia(@RequestBody Franchise franquicia) {
        Franchise NewFranchise = createFranchiseUseCase.execute(franquicia);
        return ResponseEntity.ok(NewFranchise);
    }

    @PostMapping("/{id}/sucursales")
    public ResponseEntity<Franchise> agregarSucursal(@PathVariable Long id, @RequestBody Branch sucursal) {
        Franchise newBranch = addBranchToFranchiseUseCase.execute(id, sucursal);
        return ResponseEntity.ok(newBranch);
    }


    @PutMapping("/{id}/nombre")
    public ResponseEntity<?> actualizarNombreFranquicia(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String newName = body.get("newName");
        Franchise updated = updateFranchiseNameUseCase.execute(id, newName);
        return ResponseEntity.ok(updated);
    }
}