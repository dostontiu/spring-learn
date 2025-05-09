package agg.selm.manager.repository;

import agg.selm.manager.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepository implements ProductRepository {

     private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    public InMemoryProductRepository() {
        IntStream.range(1, 4).forEach(i -> {
            this.products.add(new Product(i, "Product %d".formatted(i), "Product details %d".formatted(i)));
        });
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(this.products);
    }

    @Override
    public Product save(Product product) {
        product.setId(this.products.stream()
                .max(Comparator.comparingInt(Product::getId))
                .map(Product::getId)
                .orElse(0) + 1);
        this.products.add(product);

        return product;
    }

    @Override
    public Optional<Product> findById(int productId) {
        return this.products.stream()
                .filter(product -> Objects.equals(productId, product.getId()))
                .findFirst();
    }

    @Override
    public void deleteById(int productId) {
        this.products.removeIf(product -> Objects.equals(productId, product.getId()));
    }
}
