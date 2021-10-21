package com.dao;


import java.util.List;

import com.model.Product;

public interface ProductDAO {
	public boolean addProduct(Product product);
	public Product getProduct(int productId);
	public boolean deleteProduct(int productId);
	public boolean updateProduct(Product product);
	public boolean isProductExists(int productId);
	public List<Product>getProducts();

	
}
