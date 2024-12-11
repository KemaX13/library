package com.factoria.library.service;

import com.factoria.library.model.Product;
import com.factoria.library.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product addProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findProduct(int id) {
        return productRepository.findById(id);
    }

    public Product updatedProduct(int id, Product updatedProduct) {
        Optional<Product> foundProduct = productRepository.findById(id);

        if (foundProduct.isPresent()) {
            Product existingProduct = foundProduct.get();

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());

            return productRepository.save(existingProduct);
        }

        throw  new RuntimeException("Product not with id: " + id);
    }
}
