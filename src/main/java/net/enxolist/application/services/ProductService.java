package net.enxolist.application.services;

import net.enxolist.application.dto.ProductRequest;
import net.enxolist.domain.entities.PageResult;
import net.enxolist.domain.entities.Product;
import net.enxolist.domain.repository.ProductRepository;
import net.enxolist.infra.exception.NotFoundFailure;
import net.enxolist.infra.mapper.ProductMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductService {
    private final ImageUploaderService imageUploader;
    private final ProductMapper mapper;
    private final ProductRepository repository;

    public ProductService(ImageUploaderService imageUploader, ProductMapper mapper, ProductRepository repository) {
        this.imageUploader = imageUploader;
        this.mapper = mapper;
        this.repository = repository;
    }


    public PageResult<Product> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return PageResult.from(page);
    }

    public Product findUnique(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundFailure("Não foi encontrado nenhum produto com o id informado."));
    }

    public Product createProduct(ProductRequest request, String userID) throws IOException {
        var product = mapper.fromRequest(request);
        if (request.imageFile() != null) {
            var resultUrl = imageUploader.upload(request.imageFile(), userID);
            product.setImage(resultUrl);
        }
        product.setUserID(userID);
        return repository.save(product);
    }
}
