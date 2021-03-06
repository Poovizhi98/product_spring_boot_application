package com;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.model.Product;


public class ProductServiceControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   @Test
   public void getProductsList() throws Exception {
      
	   //localhost:8001/product		-GET ALL			GET
	   String uri = "/product";
      
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      
      assertEquals(200, status);
      
      String content = mvcResult.getResponse().getContentAsString();
      Product[] productlist = super.mapFromJson(content, Product[].class);
      assertTrue(productlist.length > 0 );
   }
   
   
   @Test
   public void getProducts() throws Exception {
      
	   //localhost:8001/product		-GET		
	   String uri = "/product/1001";
      
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      
      assertEquals(200, status);
      
      String content = mvcResult.getResponse().getContentAsString();
      Product productlist = super.mapFromJson(content, Product.class);
      assertNotNull(productlist);
   }
   //testing for conflict
   @Test
   public void createProduct() throws Exception {
      String uri = "/product";
      Product product = new Product();
      product.setProductId(5);
      product.setProductName("Bag");
      product.setQuantityOnHand(11);
      product.setPrice(11);
      String inputJson = super.mapToJson(product);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(409, status);
      
		/*
		 * String content = mvcResult.getResponse().getContentAsString();
		 * assertEquals(content, "Product is created successfully");
		 */
   }
   @Test
   public void updateProduct() throws Exception {
      String uri = "/product";
      Product product = new Product();
      product.setProductId(100);
      product.setProductName("Bag");
      product.setQuantityOnHand(11);
      product.setPrice(11);
      String inputJson = super.mapToJson(product);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      
		/*
		 * String content = mvcResult.getResponse().getContentAsString();
		 * assertEquals(content, "Product is updated successsfully");
		 */
   }
   
   
   @Test
   public void deleteProduct() throws Exception {
      String uri = "/product/100";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "Product is deleted successsfully");
   }
}