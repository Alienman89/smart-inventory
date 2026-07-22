package smart_inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import smart_inventory.model.MovementType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovementDto {

    private Long id;

    @NotNull(message = "ID produktu jest wymagane")
    private Long productId;

    private String productName;

    @NotNull(message = "Typ ruchu (IN/OUT) jest wymagany")
    private MovementType type;

    @NotNull(message = "Ilość jest wymagana")
    @Min(value = 1, message = "Ilość musi być większa od 0")
    private Integer quantity;

    private String reason;

    private LocalDateTime createdAt;
}