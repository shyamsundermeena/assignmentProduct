package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

public class ProductUpdateRequest {
	
	private @NotNull Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
