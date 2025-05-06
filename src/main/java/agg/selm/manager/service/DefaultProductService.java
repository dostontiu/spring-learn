package agg.selm.manager.service;

import agg.selm.manager.entity.Product;
import agg.selm.manager.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
