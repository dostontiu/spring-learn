package agg.selm.manager.service;

import agg.selm.manager.entity.Product;
import agg.selm.manager.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void createProduct(String name, String details) {
        this.productRepository.save(new Product(null, name, details));
    }

    @Override
    public Optional<Product> findProduct(int productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public void updateProduct(int productId, String name, String details) {
        this.productRepository.findById(productId)
                .ifPresentOrElse(product -> {
                    product.setName(name);
                    product.setDetails(details);
                }, () -> {
                    throw new NoSuchElementException("Product not found");
                });
    }

    @Override
    public void deleteProduct(int productId) {
        this.productRepository.deleteById(productId);
    }
}
