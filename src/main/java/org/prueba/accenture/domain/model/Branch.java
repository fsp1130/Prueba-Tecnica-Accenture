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


    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        if (products == null) {
            this.products = new ArrayList<>();
        } else {
            this.products = new ArrayList<>(products);
        }
    }

    public Branch(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products != null ? new ArrayList<>(products) : new ArrayList<>();
    }


}