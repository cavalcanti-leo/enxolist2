package net.enxolist.infra.mapper;

import net.enxolist.application.dto.ProductResponse;
import net.enxolist.domain.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    ProductResponse fromEntity(Product entity);

    List<ProductResponse> fromEntities(List<Product> entities);
}
