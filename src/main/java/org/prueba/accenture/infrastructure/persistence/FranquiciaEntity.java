package org.prueba.accenture.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "franquicia")
@NoArgsConstructor
public class FranquiciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "franquicia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BranchEntity> sucursales = new ArrayList<>();

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<BranchEntity> getSucursales() {
        return sucursales;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public void setSucursales(List<BranchEntity> sucursales) {
        this.sucursales = sucursales;
    }
}