package org.prueba.accenture.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Franchise {
    private Long id;
    private String name;
    private List<Branch> branches = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Franchise(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}