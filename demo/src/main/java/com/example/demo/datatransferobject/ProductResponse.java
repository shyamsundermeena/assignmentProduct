package com.example.demo.datatransferobject;

public class ProductResponse {
	
	
	String message;
	Object data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ProductResponse(String message, Object data) {
		
		this.message = message;
		this.data = data;
	}
	
	

}
