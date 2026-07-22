package smart_inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa produktu nie może być pusta")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Kod SKU jest wymagany")
    @Column(nullable = false, unique = true)
    private String sku;

    private String description;

    @NotNull(message = "Cena jest wymagana")
    @Min(value = 0, message = "Cena nie może być ujemna")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "Ilość jest wymagana")
    @Min(value = 0, message = "Ilość nie może być ujemna")
    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}