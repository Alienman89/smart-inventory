package smart_inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    @NotBlank(message = "Nazwa produktu nie może być pusta")
    private String name;

    @NotBlank(message = "Kod SKU jest wymagany")
    private String sku;

    private String description;

    @NotNull(message = "Cena jest wymagana")
    @Min(value = 0, message = "Cena nie może być ujemna")
    private BigDecimal price;

    @NotNull(message = "Ilość jest wymagana")
    @Min(value = 0, message = "Ilość nie może być ujemna")
    private Integer quantity;
    private Long categoryId;
    private String categoryName;
    private LocalDateTime createdAt;
}