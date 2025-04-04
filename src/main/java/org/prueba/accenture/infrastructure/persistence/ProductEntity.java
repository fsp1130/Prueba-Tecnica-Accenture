package org.prueba.accenture.infrastructure.persistence;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = true)
    private BranchEntity branch;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, int stock, BranchEntity branch) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BranchEntity getBranch() {
        return branch;
    }

    public void setBranch(BranchEntity branch) {
        this.branch = branch;
    }
}
