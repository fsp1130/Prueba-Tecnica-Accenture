package org.prueba.accenture.domain.model;


import java.util.ArrayList;
import java.util.List;

public class Branch {
    private Long id;
    private String name;
    private List<Product> products = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Getter para obtener la lista de productos
    public List<Product> getProducts() {
        return new ArrayList<>(products); // Devuelve una copia para evitar modificaciones externas
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Setter para la lista de productos (si necesitas establecer la lista completa)
    public void setProducts(List<Product> products) {
        if (products == null) {
            this.products = new ArrayList<>(); // Evita asignar null a la lista
        } else {
            this.products = new ArrayList<>(products); // Crea una nueva lista mutable
        }
    }

    // Constructor con inicialización
    public Branch(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products != null ? new ArrayList<>(products) : new ArrayList<>();
    }


}