package com.example.apponlineshop.service;
import com.example.apponlineshop.entity.Product;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.ReqProduct;
import com.example.apponlineshop.payload.ResProduct;
import com.example.apponlineshop.repository.CategoryRepository;
import com.example.apponlineshop.repository.MakerRepository;
import com.example.apponlineshop.repository.MeasurerRepository;
import com.example.apponlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MakerRepository makerRepository;
    @Autowired
    MeasurerRepository measureRepository;
    public ApiResponse saveProduct(ReqProduct reqProduct) {
        Product product = new Product();
        product.setName(reqProduct.getName());
        product.setCategory(categoryRepository.findById(reqProduct.getCategory_id()).orElseThrow(() -> new ResourceNotFoundException("getCategory")));
        product.setMaker(makerRepository.findById(reqProduct.getMaker_id()).orElseThrow(() -> new ResourceNotFoundException("getMaker")));
        product.setMeasure(measureRepository.findById(reqProduct.getMeasure_id()).orElseThrow(() -> new ResourceNotFoundException("getMeasure")));
        product.setExpireAmount(reqProduct.getExpireAmount());
        product.setPercentProfit(reqProduct.getPercentProfit());
        product.setTimeMode(reqProduct.getTimeMode());
        productRepository.save(product);
        return new ApiResponse("Saved Product", true);
    }
    public ResProduct getProduct(Product product) {
        return new ResProduct(
                product.getId(),
                product.getName(),
                product.getCategory().getName(),
                product.getMaker().getName(),
                product.getMeasure().getName(),
                product.getExpireAmount(),
                product.getTimeMode(),
                product.getPercentProfit()
        );
    }

    public List<ResProduct> getListProduct() {
        return productRepository.findAll().stream().map(this::getProduct).collect(Collectors.toList());
    }

    public ApiResponse editProduct(UUID id, ReqProduct reqProduct) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);

            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setName(reqProduct.getName());
                product.setCategory(categoryRepository.findById(reqProduct.getCategory_id()).orElseThrow(() -> new ResourceNotFoundException("getCategory")));
                product.setMaker(makerRepository.findById(reqProduct.getMaker_id()).orElseThrow(() -> new ResourceNotFoundException("getMaker")));
                product.setMeasure(measureRepository.findById(reqProduct.getMeasure_id()).orElseThrow(() -> new ResourceNotFoundException("getMeasure")));
                product.setExpireAmount(reqProduct.getExpireAmount());
                product.setTimeMode(reqProduct.getTimeMode());
                product.setPercentProfit(reqProduct.getPercentProfit());
                productRepository.save(product);
                return new ApiResponse("Edited client", true);

            }
            return new ApiResponse("Such Not Found", false);


        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse deleteProduct(UUID id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            productRepository.delete(product);
            return new ApiResponse("Successfully deleted product", true);
        }
        return new ApiResponse("Mavjud emas", false);
    }

}
