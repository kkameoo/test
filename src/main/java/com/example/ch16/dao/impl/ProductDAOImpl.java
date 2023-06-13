package com.example.ch16.dao.impl;

import com.example.ch16.dao.ProductDAO;
import com.example.ch16.entity.Product;
import com.example.ch16.repository.ProductRepository;
import com.example.ch16.repository.QProductRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QProductRepository qProductRepository;

    public ProductDAOImpl(ProductRepository productRepository, JPAQueryFactory jpaQueryFactory, QProductRepository qProductRepository) {
        this.productRepository = productRepository;
        this.jpaQueryFactory = jpaQueryFactory;
        this.qProductRepository = qProductRepository;
    }

    //입력
    @Override
    public Product insertProduct(Product product) {
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }
    //수정
    @Override
    public Product updateProduct(Long number, String name, int price, int stock) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);
        Product updateProduct;
        if (selectProduct.isPresent()) {
            Product product = selectProduct.get();
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }
        return updateProduct;
    }
    //삭제
    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);
        if (selectProduct.isPresent()) {
            Product product = selectProduct.get();
            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
    //모든 리스트
    @Override
    public List<Product> allProduct() {
        List<Product> allProduct = productRepository.findAll();
        return allProduct;
    }

    //orderbyprice 리스트
    @Override
    public List<Product> listOrderByPrice() {
        List<Product> listOrderByPrice = productRepository.findAllByOrderByPriceDesc();
        return listOrderByPrice;
    }
    //byname 리스트
    @Override
    public List<Product> listProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectProduct = productRepository.getReferenceById(number);
        return selectProduct;
    }
}
