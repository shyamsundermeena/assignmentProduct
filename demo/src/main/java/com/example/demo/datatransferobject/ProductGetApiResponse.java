package com.example.demo.datatransferobject;

import java.util.List;

import com.example.demo.model.ProductGetApiResponseData;


public class ProductGetApiResponse {
	
	private List<ProductGetApiResponseData> products;
	private String message;
	public List<ProductGetApiResponseData> getProducts() {
		return products;
	}
	public void setProducts(List<ProductGetApiResponseData> products) {
		this.products = products;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ProductGetApiResponse(List<ProductGetApiResponseData> products, String message) {
		super();
		this.products = products;
		this.message = message;
	}

	
	
	

}
