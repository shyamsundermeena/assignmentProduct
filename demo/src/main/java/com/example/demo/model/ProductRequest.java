package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {
	
	private @NotBlank String name;
	private @NotBlank String category;
	
	private @NotNull Integer price;
	private @NotNull Integer in_stock;
	private @NotNull Integer reserved_quantities;
	private @NotNull Boolean is_available;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getIn_stock() {
		return in_stock;
	}
	public void setIn_stock(Integer in_stock) {
		this.in_stock = in_stock;
	}
	public Integer getReserved_quantities() {
		return reserved_quantities;
	}
	public void setReserved_quantities(Integer reserved_quantities) {
		this.reserved_quantities = reserved_quantities;
	}
	public Boolean getIs_available() {
		return is_available;
	}
	public void setIs_available(Boolean is_available) {
		this.is_available = is_available;
	}
	@Override
	public String toString() {
		return "ProductRequest [name=" + name + ", category=" + category + ", price=" + price + ", in_stock=" + in_stock
				+ ", reserved_quantities=" + reserved_quantities + ", is_available=" + is_available + "]";
	}
	
	
	
	
	
	
	

}
