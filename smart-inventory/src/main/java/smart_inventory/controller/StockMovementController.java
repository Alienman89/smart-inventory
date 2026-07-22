package smart_inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart_inventory.dto.StockMovementDto;
import smart_inventory.dto.StockMovementMapper;
import smart_inventory.model.StockMovement;
import smart_inventory.service.StockMovementService;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @GetMapping
    public ResponseEntity<List<StockMovementDto>> getAllMovements() {
        List<StockMovementDto> movements = stockMovementService.getAllMovements().stream()
                .map(StockMovementMapper::toDto)
                .toList();
        return ResponseEntity.ok(movements);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockMovementDto>> getMovementsByProduct(@PathVariable Long productId) {
        List<StockMovementDto> movements = stockMovementService.getMovementsByProductId(productId).stream()
                .map(StockMovementMapper::toDto)
                .toList();
        return ResponseEntity.ok(movements);
    }

    @PostMapping
    public ResponseEntity<StockMovementDto> createMovement(@Valid @RequestBody StockMovementDto dto) {
        StockMovement movement = stockMovementService.addMovement(
                dto.getProductId(),
                dto.getType(),
                dto.getQuantity(),
                dto.getReason()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(StockMovementMapper.toDto(movement));
    }
}