package org.prueba.accenture.application.usecases;



import org.prueba.accenture.domain.model.Branch;
import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.infrastructure.mapper.BranchMapper;
import org.prueba.accenture.infrastructure.mapper.ProductMapper;
import org.prueba.accenture.infrastructure.persistence.BranchEntity;
import org.prueba.accenture.infrastructure.persistence.JpaBranchRepository;
import org.prueba.accenture.infrastructure.persistence.JpaProductRepository;
import org.prueba.accenture.infrastructure.persistence.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AddProductToBranchUseCase {

    private final JpaBranchRepository jpaBranchRepository;
    private final JpaProductRepository jpaProductRepository; // Repositorio para los productos
    private final BranchMapper branchMapper; // Mapper de Branch
    private final ProductMapper productMapper; // Mapper de Product

    @Autowired
    public AddProductToBranchUseCase(JpaBranchRepository jpaBranchRepository,
                                     JpaProductRepository jpaProductRepository,
                                     BranchMapper branchMapper,
                                     ProductMapper productMapper) {
        this.jpaBranchRepository = jpaBranchRepository;
        this.jpaProductRepository = jpaProductRepository;
        this.branchMapper = branchMapper;
        this.productMapper = productMapper;
    }


    public Branch execute(Long branchId, Product product) {
        Optional<BranchEntity> branchEntityOptional = jpaBranchRepository.findById(branchId);

        if (branchEntityOptional.isPresent()) {
            BranchEntity branchEntity = branchEntityOptional.get();

            // Mapear BranchEntity a Branch (con el mapper)
            Branch branch = branchMapper.toDomain(branchEntity);

            // Mapear el Product a ProductEntity
            ProductEntity productEntity = productMapper.toEntity(product, branchEntity);

            // Asociar el ProductEntity con la BranchEntity
            productEntity.setBranch(branchEntity);

            // Guardar el ProductEntity en la base de datos
            jpaProductRepository.save(productEntity);

            // Agregar el ProductEntity a la lista de productos de la BranchEntity
            branchEntity.getProducts().add(productEntity);

            // Guardar la sucursal con el producto agregado
            jpaBranchRepository.save(branchEntity);

            return branch;
        }

        throw new RuntimeException("Sucursal no encontrada");
    }
}

