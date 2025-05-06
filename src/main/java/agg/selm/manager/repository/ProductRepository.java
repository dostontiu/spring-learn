package agg.selm.manager.repository;

import agg.selm.manager.entity.Product;

import java.util.List;

public interface ProductRepository {
    public List<Product> findAll();
}
