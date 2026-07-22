package smart_inventory.dto;

import smart_inventory.model.StockMovement;

public class StockMovementMapper {

    public static StockMovementDto toDto(StockMovement movement) {
        if (movement == null) return null;
        return StockMovementDto.builder()
                .id(movement.getId())
                .productId(movement.getProduct() != null ? movement.getProduct().getId() : null)
                .productName(movement.getProduct() != null ? movement.getProduct().getName() : null)
                .type(movement.getType())
                .quantity(movement.getQuantity())
                .reason(movement.getReason())
                .createdAt(movement.getCreatedAt())
                .build();
    }
}