package smart_inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart_inventory.model.Category;
import smart_inventory.model.Product;
import smart_inventory.repository.CategoryRepository;
import smart_inventory.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono produktu o ID: " + id));
    }

    @Transactional
    public Product createProduct(Product product, Long categoryId) {
        if (productRepository.existsBySku(product.getSku())) {
            throw new IllegalArgumentException("Produkt o kodzie SKU: " + product.getSku() + " już istnieje!");
        }

        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o ID: " + categoryId));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }

    @Transactional
    public Product updateStock(Long productId, Integer quantityChange) {
        Product product = getProductById(productId);
        int newQuantity = product.getQuantity() + quantityChange;

        if (newQuantity < 0) {
            throw new IllegalArgumentException("Niewystarczająca ilość towaru w magazynie!");
        }

        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Nie znaleziono produktu o ID: " + id);
        }
        productRepository.deleteById(id);
    }
}