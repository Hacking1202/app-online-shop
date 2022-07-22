package com.example.apponlineshop.controller;

import com.example.apponlineshop.entity.Product;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.ReqProduct;
import com.example.apponlineshop.payload.ResProduct;
import com.example.apponlineshop.repository.ProductRepository;
import com.example.apponlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/product")
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @PostMapping
    @ResponseBody
    public HttpEntity<?> saveProduct(@RequestBody ReqProduct reqProduct) {
        ApiResponse apiResponse = productService.saveProduct(reqProduct);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResProduct getOneProduct(Product product){
        return productService.getProduct(product);
    }

    @GetMapping()
    @ResponseBody
    public List<ResProduct> getList(){
        return productService.getListProduct();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> editProduct(@PathVariable UUID id, @RequestBody ReqProduct reqProduct) {
        ApiResponse apiResponse = productService.editProduct(id, reqProduct);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResponse deleteProduct(@PathVariable UUID id){

        return productService.deleteProduct(id);
    }

}
