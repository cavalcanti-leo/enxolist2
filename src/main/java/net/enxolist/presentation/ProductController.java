package net.enxolist.presentation;

import net.enxolist.application.dto.ProductRequest;
import net.enxolist.application.dto.ProductResponse;
import net.enxolist.application.services.ProductService;
import net.enxolist.domain.entities.PageResult;
import net.enxolist.infra.mapper.ProductMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public PageResult<ProductResponse> list(Pageable pageable) {
        var result = service.findAll(pageable);
        return result.map(mapper::fromEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable String id) {
        var result = service.findUnique(id);
        return ResponseEntity.ok(mapper.fromEntity(result));
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponse> create(@Validated @ModelAttribute ProductRequest request) throws IOException {
        var result = service.createProduct(request, UUID.randomUUID().toString());
        return ResponseEntity.ok(mapper.fromEntity(result));
    }
}
