package com.example.ch16.controller;

import com.example.ch16.dto.ChangeProductDto;
import com.example.ch16.dto.ProductDto;
import com.example.ch16.dto.ProductResponseDto;
import com.example.ch16.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //입력
    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    //수정
    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ChangeProductDto changeProductDto) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProduct(
                changeProductDto.getNumber(), changeProductDto.getName(), changeProductDto.getPrice(),changeProductDto.getStock());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    //삭제
    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
    //모든 리스트
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> allProduct() {
        List<ProductResponseDto> productResponseDto = productService.allProduct();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    //orderbyprice 리스트
    @GetMapping("/listOrderByPrice")
    public ResponseEntity<List<ProductResponseDto>> listOrderByPrice() {
        List<ProductResponseDto> productResponseDto = productService.listOrderByPrice();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    //buname 리스트
    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponseDto>> listByName(String name) {
        List<ProductResponseDto> productResponseDto = productService.listByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    //상품 정보
    @GetMapping("/")
    public ResponseEntity<ProductResponseDto> getProduct(Long number) {
        ProductResponseDto productResponseDto = productService.getproduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

}
