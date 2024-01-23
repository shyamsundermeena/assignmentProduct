package com.example.demo.productController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.datatransferobject.ProductGetApiByIdResponse;
import com.example.demo.datatransferobject.ProductGetApiResponse;
import com.example.demo.datatransferobject.ProductPostApiResponse;
import com.example.demo.datatransferobject.ProductResponse;
import com.example.demo.model.ProductRequest;
import com.example.demo.model.ProductUpdateRequest;
import com.example.demo.productService.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	
	@PostMapping("/products")
	public ResponseEntity<ProductPostApiResponse> addProduct(@RequestBody(required=true) @Valid final ProductRequest productRequest){
		
		return productService.addProduct(productRequest);
		
		
	}
	
	@GetMapping("/products")
	public ResponseEntity<ProductGetApiResponse> getProduct(){
		
		return productService.getProduct();
		
		
	}
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable int id){
		
		return productService.getProductById(id);
		
		
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@RequestBody(required=true) @Valid final ProductUpdateRequest productUpdateRequest,@PathVariable int id){
		
		return productService.updateProduct(productUpdateRequest,id);
		
		
	}

}
