package com.example.demo.productService;



import com.example.demo.constant.ProductConstant;
import com.example.demo.datatransferobject.ProductGetApiByIdResponse;
import com.example.demo.datatransferobject.ProductGetApiResponse;
import com.example.demo.datatransferobject.ProductPostApiResponse;
import com.example.demo.datatransferobject.ProductResponse;
import com.example.demo.datatransferobject.ProductUpdateApiResponse;
import com.example.demo.mapper.ProductMapperImpl;
import com.example.demo.model.ProductGetApiResponseData;
import com.example.demo.model.ProductRequest;
import com.example.demo.model.ProductUpdateRequest;
import com.example.demo.productEntity.Product;
import com.example.demo.productRepository.ProductRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapperImpl productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct() {
        ProductRequest productRequest = new ProductRequest(); // create a test request

        when(productRepository.save(any())).thenReturn(new Product());
        when(productMapper.mapProductCreationData(any())).thenReturn(new Product());

        ResponseEntity<ProductPostApiResponse> response = productService.addProduct(productRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_CREATED, response.getBody().getMessage());
        // add more assertions based on your specific logic
    }
    @Test
    public void testAddProductFailure() {
        ProductRequest productRequest = new ProductRequest(); // create a test request

        when(productRepository.save(any())).thenThrow(new RuntimeException());
        when(productMapper.mapProductCreationData(any())).thenReturn(null);

        ResponseEntity<ProductPostApiResponse> response = productService.addProduct(productRequest);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals(ProductConstant.MESSAGE_API_RESPONSE_GENERIC_EXCEPTION, response.getBody().getMessage());
        // add more assertions based on your specific logic
    }

    @Test
    public void testGetProduct() {
    	 Product product=new Product();
         product.setCategory("sdsadasd");
         product.setIn_stock(485);
         product.setIs_available(true);
         product.setName("p1");
         product.setPrice(53);
         product.setProductId(19);
         product.setReserved_quantities(2);
         when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
         List<ProductGetApiResponseData> productGetApiResponseDataList=new ArrayList<>();
         
         when(productMapper.mapProductList(any())).thenReturn(productGetApiResponseDataList);

         ResponseEntity<ProductGetApiResponse> response = productService.getProduct();

         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_LIST, response.getBody().getMessage());
         // add more assertions based on your specific logic
     }
    @Test
   public void testGetProductListEmpty() {
    	List<Product> productList =new ArrayList();
        when(productRepository.findAll()).thenReturn(productList);
        List<ProductGetApiResponseData> productGetApiResponseDataList=new ArrayList<>();
        when(productMapper.mapProductList(any())).thenReturn(productGetApiResponseDataList);

        ResponseEntity<ProductGetApiResponse> response = productService.getProduct();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_NO_PRODUCT_EXIST, response.getBody().getMessage());
        // add more assertions based on your specific logic
    }
    @Test
    public void testGetProductListFailure() {
     	
         when(productRepository.findAll()).thenReturn(null);
         List<ProductGetApiResponseData> productGetApiResponseDataList=new ArrayList<>();
         when(productMapper.mapProductList(any())).thenReturn(productGetApiResponseDataList);

         ResponseEntity<ProductGetApiResponse> response = productService.getProduct();

         assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
         // add more assertions based on your specific logic
     }

    @Test
   public void testGetProductById() {
        int productId = 19; // specify the test product ID
        ProductGetApiByIdResponse productGetApiByIdResponse=new ProductGetApiByIdResponse();
        Product product=new Product();
        product.setCategory("sdsadasd");
        product.setIn_stock(485);
        product.setIs_available(true);
        product.setName("p1");
        product.setPrice(53);
        product.setProductId(19);
        product.setReserved_quantities(2);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.mapProductGetByIdData(any())).thenReturn(productGetApiByIdResponse);

        ResponseEntity<ProductResponse> response = productService.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_DATA, response.getBody().getMessage());
        // add more assertions based on your specific logic
    }
    
    @Test
    public void testGetProductByIdException() {
         int productId = 0; // specify the test product ID
         ProductGetApiByIdResponse productGetApiByIdResponse=new ProductGetApiByIdResponse();
//         Product product=new Product();
//         product.setCategory("sdsadasd");
//         product.setIn_stock(485);
//         product.setIs_available(true);
//         product.setName("p1");
//         product.setPrice(53);
//         product.setProductId(19);
//         product.setReserved_quantities(2);
         when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));
         when(productMapper.mapProductGetByIdData(any())).thenReturn(productGetApiByIdResponse);

         ResponseEntity<ProductResponse> response = productService.getProductById(productId);

         assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        // assertEquals(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCTID_NOT_EXIST, response.getBody().getMessage());
         // add more assertions based on your specific logic
     }
    @Test
    public void testGetProductByIdFailure() {
         int productId = 19; // specify the test product ID
         ProductGetApiByIdResponse productGetApiByIdResponse=new ProductGetApiByIdResponse();
         Product product=new Product();
         product.setCategory("sdsadasd");
         product.setIn_stock(485);
         product.setIs_available(true);
         product.setName("p1");
         product.setPrice(53);
         product.setProductId(15);
         product.setReserved_quantities(2);
         product.setIs_available(false);
         when(productRepository.findById(productId)).thenReturn(Optional.of(product));
         when(productMapper.mapProductGetByIdData(any())).thenReturn(productGetApiByIdResponse);

         ResponseEntity<ProductResponse> response = productService.getProductById(productId);

         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCTID_NOT_EXIST, response.getBody().getMessage());
         // add more assertions based on your specific logic
     }
    @Test
   public void testUpdateProduct() {
        int productId = 19; // specify the test product ID
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
        productUpdateRequest.setQuantity(2);
        Product product=new Product();
        product.setCategory("sdsadasd");
        product.setIn_stock(485);
        product.setIs_available(true);
        product.setName("p1");
        product.setPrice(53);
        product.setProductId(19);
        product.setReserved_quantities(2);
        // create a test request
        ProductUpdateApiResponse productUpdateApiResponse=new ProductUpdateApiResponse();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.mapProductUpdateDBData(new Product(), 2)).thenReturn(product);
        when(productMapper.mapProductUpdateResponse(new Product(), 2)).thenReturn(productUpdateApiResponse);

        ResponseEntity<ProductResponse> response = productService.updateProduct(productUpdateRequest, productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_UPDATE, response.getBody().getMessage());
        // add more assertions based on your specific logic
    }
    
    @Test
    public void testUpdateProductFailure() {
         int productId = 15; // specify the test product ID
         ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
         productUpdateRequest.setQuantity(2);
         Product product=new Product();
         product.setCategory("sdsadasd");
         product.setIn_stock(485);
         product.setIs_available(false);
         product.setName("p1");
         product.setPrice(53);
         product.setProductId(19);
         product.setReserved_quantities(2);
         // create a test request
         ProductUpdateApiResponse productUpdateApiResponse=new ProductUpdateApiResponse();
         when(productRepository.findById(productId)).thenReturn(Optional.of(product));
         when(productMapper.mapProductUpdateDBData(new Product(), 2)).thenReturn(product);
         when(productMapper.mapProductUpdateResponse(new Product(), 2)).thenReturn(productUpdateApiResponse);

         ResponseEntity<ProductResponse> response = productService.updateProduct(productUpdateRequest, productId);

         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCTID_NOT_EXIST, response.getBody().getMessage());
         // add more assertions based on your specific logic
     }
    
    @Test
    public void testUpdateProductFailureInsufficientStock() {
         int productId = 19; // specify the test product ID
         ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
         productUpdateRequest.setQuantity(2);
         Product product=new Product();
         product.setCategory("sdsadasd");
         product.setIn_stock(485);
         product.setIs_available(false);
         product.setName("p1");
         product.setPrice(53);
         product.setProductId(19);
         product.setReserved_quantities(2);
         // create a test request
         ProductUpdateApiResponse productUpdateApiResponse=new ProductUpdateApiResponse();
         when(productRepository.findById(productId)).thenReturn(Optional.of(product));
         when(productMapper.mapProductUpdateDBData(new Product(), 2)).thenReturn(product);
         when(productMapper.mapProductUpdateResponse(new Product(), 2)).thenReturn(productUpdateApiResponse);

         ResponseEntity<ProductResponse> response = productService.updateProduct(productUpdateRequest, productId);

         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals(ProductConstant.MESSAGE_API_RESPONSE_SUCCESS_PRODUCT_UPDATE_INSUFFICIENT_PRODUCT_STOCK, response.getBody().getMessage());
         // add more assertions based on your specific logic
     }
}
