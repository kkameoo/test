package com.example.ch16.service.impl;

import com.example.ch16.dao.ProductDAO;
import com.example.ch16.dto.ProductDto;
import com.example.ch16.dto.ProductResponseDto;
import com.example.ch16.entity.Product;
import com.example.ch16.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    //입력
    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saveProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setNumber(saveProduct.getNumber());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());
        return productResponseDto;
    }

    //수정
    @Override
    public ProductResponseDto changeProduct(Long number, String name, int price, int stock) throws Exception {
        Product changeproduct = productDAO.updateProduct(number, name, price, stock);
        return new ProductResponseDto(changeproduct);
    }
    //삭제
    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
    //모든 리스트
    @Override
    public List<ProductResponseDto> allProduct() {
        List<Product> products = productDAO.allProduct();
        List<ProductResponseDto> productResponseDtoList = products.stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return productResponseDtoList;
    }
    //orderbyprice 리스트
    @Override
    public List<ProductResponseDto> listOrderByPrice() {
        List<Product> products = productDAO.listOrderByPrice();
        List<ProductResponseDto> productResponseDtoList = products.stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return productResponseDtoList;
    }
    // 이름 리스트
    @Override
    public List<ProductResponseDto> listByName(String name) {
        List<Product> products = productDAO.listProductByName(name);
        List<ProductResponseDto> productResponseDtoList = products.stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return productResponseDtoList;
    }
    // 상품정보
    @Override
    public ProductResponseDto getproduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(product.getName());
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }
}
