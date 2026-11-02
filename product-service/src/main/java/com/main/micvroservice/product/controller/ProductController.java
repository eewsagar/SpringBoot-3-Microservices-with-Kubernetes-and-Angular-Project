package com.main.micvroservice.product.controller;

import com.main.micvroservice.product.dto.ProductRequest;
import com.main.micvroservice.product.dto.ProductResponse;
import com.main.micvroservice.product.model.Product;
import com.main.micvroservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProduct(){
        return productService.getProduct();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }
}
