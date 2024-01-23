package com.example.demo.productService;

import org.springframework.http.ResponseEntity;

import com.example.demo.datatransferobject.ProductGetApiByIdResponse;
import com.example.demo.datatransferobject.ProductGetApiResponse;
import com.example.demo.datatransferobject.ProductPostApiResponse;
import com.example.demo.datatransferobject.ProductResponse;
import com.example.demo.model.ProductRequest;
import com.example.demo.model.ProductUpdateRequest;

public interface ProductService {
	
	public ResponseEntity<ProductPostApiResponse> addProduct(ProductRequest productRequest);
	
	public ResponseEntity<ProductGetApiResponse> getProduct();
	public ResponseEntity<ProductResponse> getProductById(int id);
	public ResponseEntity<ProductResponse> updateProduct(ProductUpdateRequest productRequest,int id);

}
