package com.example.demo.productService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.constant.ProductConstant;
import com.example.demo.datatransferobject.ProductGetApiByIdResponse;
import com.example.demo.datatransferobject.ProductGetApiResponse;
import com.example.demo.datatransferobject.ProductPostApiResponse;
import com.example.demo.datatransferobject.ProductResponse;
import com.example.demo.datatransferobject.ProductUpdateApiResponse;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.mapper.ProductMapperImpl;
import com.example.demo.model.ProductGetApiResponseData;
import com.example.demo.model.ProductRequest;
import com.example.demo.model.ProductUpdateRequest;
import com.example.demo.productController.ProductController;
import com.example.demo.productEntity.Product;
import com.example.demo.productRepository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductMapper productMapper;
	
	@Override
	public ResponseEntity<ProductPostApiResponse> addProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		logger.info("productRequest service "+productRequest);
		try {

			
			
			productRepository.save(productMapper.mapProductCreationData(productRequest));
			
			return new ResponseEntity<ProductPostApiResponse>( new ProductPostApiResponse(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_CREATED),HttpStatus.CREATED);
		}catch(Exception e) {
			
			return new ResponseEntity<ProductPostApiResponse>( new ProductPostApiResponse(ProductConstant.MESSAGE_API_RESPONSE_GENERIC_EXCEPTION),HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
		
		
	}
	@Override
	public ResponseEntity<ProductGetApiResponse> getProduct(){
		
		
		try {
			List<Product> productList=productRepository.findAll();
			if(productList.size()!=0) {
			return new ResponseEntity<ProductGetApiResponse>( new ProductGetApiResponse(productMapper.mapProductList(productList),ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_LIST),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<ProductGetApiResponse>( new ProductGetApiResponse(null,ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_NO_PRODUCT_EXIST),HttpStatus.OK);
				
					
			}
		}catch(Exception e) {
			
			return new ResponseEntity<ProductGetApiResponse>( new ProductGetApiResponse(null,e.getMessage()),HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
		
		
	}

	@Override
	public ResponseEntity<ProductResponse>  getProductById(int id){
		
		
		try {
			logger.info("id"+id);
			Optional<Product> product=productRepository.findById(id);
			
			boolean isProductExist=product.filter(prod-> prod.getProductId()==id).isPresent();
			
			if(isProductExist) {
				return new ResponseEntity<ProductResponse>(new ProductResponse(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_DATA,productMapper.mapProductGetByIdData(product.get())),HttpStatus.OK);
				
			}else {
				return new ResponseEntity<ProductResponse>(new ProductResponse(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCTID_NOT_EXIST,null),HttpStatus.OK);
				
				
			}
			
			
			}catch(Exception e) {
			
				return new ResponseEntity<ProductResponse>(new ProductResponse(e.getMessage(),null),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		
	}
	
	@Override
	public ResponseEntity<ProductResponse> updateProduct(ProductUpdateRequest productRequest,int id) {
		// TODO Auto-generated method stub
		logger.info("productRequest service "+productRequest);
		try {

			Optional<Product> product=productRepository.findById(id);
			
			boolean isProductExist=product.filter(prod-> prod.getProductId()==id).isPresent();
			if(isProductExist) {
				if(product.get().getIs_available() &&((product.get().getIn_stock()-productRequest.getQuantity())>=0)) {
					productRepository.save(productMapper.mapProductUpdateDBData(product.get(),productRequest.getQuantity()));
					return new ResponseEntity<ProductResponse>(new ProductResponse(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_UPDATE,productMapper.mapProductUpdateResponse(product.get(),productRequest.getQuantity())),HttpStatus.OK);
					
				}else {
					return new ResponseEntity<ProductResponse>(new ProductResponse(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_UPDATE_INSUFFICIENT_PRODUCT_STOCK,null),HttpStatus.OK);
						
				}
				
			}else {
				return new ResponseEntity<ProductResponse>(new ProductResponse(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCTID_NOT_EXIST,null),HttpStatus.OK);
				
				
			}
			
			
			}catch(Exception e) {
			
				return new ResponseEntity<ProductResponse>(new ProductResponse(e.getMessage(),null),HttpStatus.UNPROCESSABLE_ENTITY);
				
		}
		
		
	}

}
