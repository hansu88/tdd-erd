package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.ProductDto;
import com.h2.hhpluserdjvm.dto.ProductOptionDto;
import com.h2.hhpluserdjvm.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}/stock")
    public int getProductStock(@PathVariable Long productId) {
        return productService.getTotalStock(productId);
    }
}
