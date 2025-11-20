package net.enxolist.application.services;

import net.enxolist.domain.entities.PageResult;
import net.enxolist.domain.entities.Product;
import net.enxolist.domain.repository.ProductRepository;
import net.enxolist.infra.exception.NotFoundFailure;
import net.enxolist.infra.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductMapper mapper;
    private final ProductRepository repository;


    public ProductService(ProductMapper mapper, ProductRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public PageResult<Product> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return PageResult.from(page);
    }

    public Product findUnique(String id){
        return repository.findById(id).orElseThrow(() -> new NotFoundFailure("Não foi encontrado nenhum produto com o id informado."));
    }
}
