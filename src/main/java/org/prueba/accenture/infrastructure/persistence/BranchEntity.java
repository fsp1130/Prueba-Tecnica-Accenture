package org.prueba.accenture.infrastructure.persistence;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "branch")
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "franquicia_id") // Ajusta según el nombre real de la columna
    private FranquiciaEntity franquicia;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    public BranchEntity() {
    }

    public FranquiciaEntity getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(FranquiciaEntity franquicia) {
        this.franquicia = franquicia;
    }

    public BranchEntity(Long id, String name, List<ProductEntity> products) {
        this.id = id;
        this.name = name;
        this.products = products;
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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
