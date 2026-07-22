package smart_inventory.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import smart_inventory.model.MovementType;
import smart_inventory.model.Product;
import smart_inventory.model.StockMovement;
import smart_inventory.repository.ProductRepository;
import smart_inventory.repository.StockMovementRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockMovementServiceTest {

    @Mock
    private StockMovementRepository stockMovementRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private StockMovementService stockMovementService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = Product.builder()
                .id(1L)
                .name("Klawiatura Testowa")
                .sku("TEST-001")
                .price(new BigDecimal("100.00"))
                .quantity(10)
                .build();
    }

    @Test
    @DisplayName("Powinno pomyślnie zwiększyć stan magazynowy przy ruchu typu IN")
    void shouldIncreaseStockOnIncomingMovement() {

        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(stockMovementRepository.save(any(StockMovement.class))).thenAnswer(i -> i.getArgument(0));

        StockMovement movement = stockMovementService.addMovement(1L, MovementType.IN, 5, "Dostawa");

        assertThat(testProduct.getQuantity()).isEqualTo(15);
        assertThat(movement.getQuantity()).isEqualTo(5);
        verify(productRepository, times(1)).save(testProduct);
    }

    @Test
    @DisplayName("Powinno rzucić wyjątek gdy chcemy wydać (OUT) więcej towaru niż jest na stanie")
    void shouldThrowExceptionWhenNotEnoughStockForOutgoingMovement() {

        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        assertThatThrownBy(() -> stockMovementService.addMovement(1L, MovementType.OUT, 20, "Wydanie nadmiarowe"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Niewystarczająca ilość towaru");

        verify(stockMovementRepository, never()).save(any());
    }
}