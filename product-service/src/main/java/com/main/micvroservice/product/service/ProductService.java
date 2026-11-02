package com.main.micvroservice.product.service;

import com.main.micvroservice.product.dto.ProductRequest;
import com.main.micvroservice.product.dto.ProductResponse;
import com.main.micvroservice.product.model.Product;
import com.main.micvroservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public ProductResponse createProduct(ProductRequest productRequest){
      Product product = Product.builder()
              .name(productRequest.name())
              .description(productRequest.description())
              .price(productRequest.price())
              .build();
      productRepository.save(product);
      log.info("product created successfully");
      return new ProductResponse(product.getId(), product.getName(),product.getDescription()
              ,product.getPrice());
  }

    public List<ProductResponse> getProduct() {
      return productRepository.findAll()
              .stream()
              .map(product -> new ProductResponse(product.getId(), product.getName(),product.getDescription()
              ,product.getPrice()))
              .toList();
    }
}
