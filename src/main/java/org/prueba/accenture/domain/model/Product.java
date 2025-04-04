package org.prueba.accenture.domain.model;


public class Product {
    private Long id;
    private String name;
    private int stock;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Product(Long id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }
}