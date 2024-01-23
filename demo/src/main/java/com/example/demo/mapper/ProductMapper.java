package com.example.demo.mapper;

import java.util.List;

import com.example.demo.datatransferobject.ProductGetApiByIdResponse;
import com.example.demo.datatransferobject.ProductUpdateApiResponse;
import com.example.demo.model.ProductGetApiResponseData;
import com.example.demo.model.ProductRequest;
import com.example.demo.productEntity.Product;

public interface ProductMapper {
	
	public Product mapProductCreationData(ProductRequest productRequest);
	public ProductGetApiByIdResponse mapProductGetByIdData(Product product);
	public List<ProductGetApiResponseData> mapProductList(List<Product> productList);
	public ProductUpdateApiResponse mapProductUpdateResponse(Product product,int quantity);
	public Product mapProductUpdateDBData(Product product,int quantity);

}
