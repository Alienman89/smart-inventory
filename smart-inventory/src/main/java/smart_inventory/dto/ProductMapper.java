package smart_inventory.dto;

import smart_inventory.model.Product;

public class ProductMapper {

    public static ProductDto toDto(Product product) {
        if (product == null) return null;

        Long catId = product.getCategory() != null ? product.getCategory().getId() : null;
        String catName = product.getCategory() != null ? product.getCategory().getName() : null;

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .categoryId(catId)
                .categoryName(catName)
                .build();
    }

    public static Product toEntity(ProductDto dto) {
        if (dto == null) return null;
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .sku(dto.getSku())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}