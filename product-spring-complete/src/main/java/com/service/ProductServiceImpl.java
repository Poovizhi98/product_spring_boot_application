package com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDAO;
import com.model.Product;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	public boolean addProduct(Product product) {
		System.out.println("Inside product"+product);
		SendEmailTLS emailTLS = new SendEmailTLS();
		emailTLS.sendEmail("Product "+product.getProductName()+" added", "Price is :"+ product.getPrice());
		return productDAO.addProduct(product);
	}
	

	@Override
	public Product getProduct(int productId) {

		// TODO Auto-generated method stub
		return productDAO.getProduct(productId);
	}

	@Override
	public boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return productDAO.deleteProduct(productId);
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDAO.updateProduct(product);
	}

	@Override
	public boolean isProductExists(int productId) {
		// TODO Auto-generated method stub
		return productDAO.isProductExists(productId);
	}

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return productDAO.getProducts();
	}}