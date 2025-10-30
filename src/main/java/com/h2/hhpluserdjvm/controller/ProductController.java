package com.h2.hhpluserdjvm.controller;

import com.h2.hhpluserdjvm.dto.product.PopularProductResponseDto;
import com.h2.hhpluserdjvm.dto.product.ProductResponseDto;
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
    public List<ProductResponseDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}/stock")
    public int getProductStock(@PathVariable Long productId) {
        return productService.getTotalStock(productId);
    }

    @GetMapping("/popular")
    public List<PopularProductResponseDto> getPopularProducts(
            @RequestParam(defaultValue = "3") int days,
            @RequestParam(defaultValue = "5") int limit) {
        return productService.getPopularProducts(days, limit);
    }
}
