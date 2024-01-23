package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.datatransferobject.ProductGetApiByIdResponse;
import com.example.demo.datatransferobject.ProductPostApiResponse;
import com.example.demo.datatransferobject.ProductResponse;
import com.example.demo.datatransferobject.ProductUpdateApiResponse;
import com.example.demo.model.ProductGetApiResponseData;
import com.example.demo.model.ProductRequest;
import com.example.demo.productEntity.Product;
import com.example.demo.productService.ProductServiceImpl;

@Component
public class ProductMapperImpl implements ProductMapper{
	private static final Logger logger = LoggerFactory.getLogger(ProductMapperImpl.class);
	@Override
	public Product mapProductCreationData(ProductRequest productRequest) {
		Product product=new Product();
		product.setName(productRequest.getName());
		product.setCategory(productRequest.getCategory());
		product.setPrice(productRequest.getPrice());
		product.setIn_stock(productRequest.getIn_stock());
		product.setReserved_quantities(productRequest.getReserved_quantities());
		product.setIs_available(productRequest.getIs_available());
		return product;
	}
	@Override
	public ProductGetApiByIdResponse mapProductGetByIdData(Product product) {
		
		ProductGetApiByIdResponse productGetApiByIdResponse=new ProductGetApiByIdResponse();
		productGetApiByIdResponse.setName(product.getName());
		productGetApiByIdResponse.setCategory(product.getCategory());
		productGetApiByIdResponse.setPrice(product.getPrice());
		productGetApiByIdResponse.setIn_stock(product.getIn_stock());
		productGetApiByIdResponse.setReserved_quantities(product.getReserved_quantities());
		productGetApiByIdResponse.setIs_available(product.getIs_available());
		
		return productGetApiByIdResponse;
	}
	@Override
	public List<ProductGetApiResponseData> mapProductList(List<Product> productList){
		
		
		return productList.stream()
                .map(product -> new ProductGetApiResponseData(
                        product.getName(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getIn_stock(),
                        product.getReserved_quantities(),
                        product.getIs_available()
                ))
                .collect(Collectors.toList());
		
	}
	@Override
	public ProductUpdateApiResponse mapProductUpdateResponse(Product product,int quantity) {
		
		
		ProductUpdateApiResponse productUpdateApiResponse=new ProductUpdateApiResponse();
		productUpdateApiResponse.setName(product.getName());
		productUpdateApiResponse.setCategory(product.getCategory());
		productUpdateApiResponse.setPrice(product.getPrice());
		
		productUpdateApiResponse.setIn_stock(product.getIn_stock()-quantity);
		productUpdateApiResponse.setReserved_quantities(quantity);
		productUpdateApiResponse.setIs_available(product.getIs_available());
		
		return productUpdateApiResponse;
	}
	
	@Override
	public Product mapProductUpdateDBData(Product product,int quantity) {
		
		
		product.setIn_stock(product.getIn_stock()-quantity);
		product.setReserved_quantities(quantity);
		
		
		return product;
	}
}
