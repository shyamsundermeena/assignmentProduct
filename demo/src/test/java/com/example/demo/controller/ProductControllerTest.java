package com.example.demo.controller;

import com.example.demo.datatransferobject.ProductGetApiByIdResponse;
import com.example.demo.datatransferobject.ProductGetApiResponse;
import com.example.demo.datatransferobject.ProductPostApiResponse;
import com.example.demo.datatransferobject.ProductResponse;
import com.example.demo.model.ProductRequest;
import com.example.demo.model.ProductUpdateRequest;
import com.example.demo.productController.ProductController;
import com.example.demo.productService.ProductService;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Before
   public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public  void testAddProduct() {
        ProductRequest productRequest = new ProductRequest(); // create a test request
        when(productService.addProduct(any())).thenReturn(ResponseEntity.ok(new ProductPostApiResponse("Product Created")));

        ResponseEntity<ProductPostApiResponse> response = productController.addProduct(productRequest);

        assertEquals(200, response.getStatusCodeValue());
        // add more assertions based on your specific logic
    }

    @Test
    public void testGetProduct() {
        when(productService.getProduct()).thenReturn(ResponseEntity.ok(new ProductGetApiResponse(null,"product list")));

        ResponseEntity<ProductGetApiResponse> response = productController.getProduct();

        assertEquals(200, response.getStatusCodeValue());
        // add more assertions based on your specific logic
    }

    @Test
   public void testGetProductById() {
        int productId = 1; // specify the test product ID
        
        when(productService.getProductById(productId)).thenReturn(ResponseEntity.ok(new ProductResponse(null, productId)));

        ResponseEntity<ProductResponse> response = productController.getProduct(productId);

        assertEquals(200, response.getStatusCodeValue());
        // add more assertions based on your specific logic
    }

    @Test
   public void testUpdateProduct() {
        int productId = 1; // specify the test product ID
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(); // create a test request
        when(productService.updateProduct(productUpdateRequest, 1)).thenReturn(ResponseEntity.ok(new ProductResponse(null, productUpdateRequest)));

        ResponseEntity<ProductResponse> response = productController.updateProduct(productUpdateRequest, productId);

        assertEquals(200, response.getStatusCodeValue());
        // add more assertions based on your specific logic
    }
}

