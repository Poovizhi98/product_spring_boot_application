package com.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dao.ProductDAO;
import com.service.ProductService;
import com.model.Product;
@RestController
@RequestMapping("product")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:4200"})
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		System.out.println("All products called ");
		List <Product> allProducts= productService.getProducts();
		return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
	}
	
	//getting a single product
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct2(@PathVariable("productId")int productId) {
		Product product=new Product();
		if(productService.isProductExists(productId)) {
			product=productService.getProduct(productId);
			return new ResponseEntity<Product>(product,HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<Product>(product,HttpStatus.NO_CONTENT);
		}
		
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId")int productId) {
		Product product=new Product();
		if(productService.isProductExists(productId))
		{
			productService.deleteProduct(productId);
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		if(productService.isProductExists(product.getProductId()))
		{
			
			return new ResponseEntity<Product>(product,HttpStatus.CONFLICT);
		}
		else
		{
			productService.addProduct(product);
			return new ResponseEntity<Product>(product,HttpStatus.CREATED);
		}
		
		
	}
	
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		
		if(productService.isProductExists(product.getProductId()))
		{
			productService.updateProduct(product);
			return new ResponseEntity<Product>(product,HttpStatus.IM_USED);
		}
		else
		{
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
		
		
	}
	


}
