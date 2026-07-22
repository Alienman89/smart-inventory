package smart_inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart_inventory.model.MovementType;
import smart_inventory.model.Product;
import smart_inventory.model.StockMovement;
import smart_inventory.repository.ProductRepository;
import smart_inventory.repository.StockMovementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public List<StockMovement> getAllMovements() {
        return stockMovementRepository.findAll();
    }

    public List<StockMovement> getMovementsByProductId(Long productId) {
        return stockMovementRepository.findByProductIdOrderByCreatedAtDesc(productId);
    }

    @Transactional
    public StockMovement addMovement(Long productId, MovementType type, Integer quantity, String reason) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Ilość w ruchu magazynowym musi być większa od 0!");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono produktu o ID: " + productId));

        int newQuantity;
        if (type == MovementType.IN) {
            newQuantity = product.getQuantity() + quantity;
        } else {
            newQuantity = product.getQuantity() - quantity;
            if (newQuantity < 0) {
                throw new IllegalArgumentException("Niewystarczająca ilość towaru w magazynie na wydanie!");
            }
        }

        product.setQuantity(newQuantity);
        productRepository.save(product);

        StockMovement movement = StockMovement.builder()
                .product(product)
                .type(type)
                .quantity(quantity)
                .reason(reason)
                .build();

        return stockMovementRepository.save(movement);
    }
}