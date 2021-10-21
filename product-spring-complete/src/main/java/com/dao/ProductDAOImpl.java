package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.model.Product;
import com.mongodb.WriteResult;
@Repository
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	MongoTemplate mongoTemplate;
	
	public boolean addProduct(Product product) {
		System.out.println("Inside product"+product);
		mongoTemplate.save(product);
		return false;
	}

	

	@Override
	public Product getProduct(int productId) {
		return mongoTemplate.findById(productId, Product.class,"product");
	}

	@Override
	public boolean deleteProduct(int productId) {

		Product product=new Product();
		product.setProductId(productId);

		WriteResult writeResult= mongoTemplate.remove(product);
		System.out.println(writeResult);
		int rowsAffected=writeResult.getN();
		if(rowsAffected==0) {
		return false;}
		else
			return true;
	}

	@Override
	public boolean updateProduct(Product product) {
		//mongoTemplate.save(product);
		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is(product.getProductId()));
		Update update=new Update();
		update.set("productName",product.getProductName());
		update.set("quantityOnHand",product.getQuantityOnHand());
		update.set("price",product.getPrice());
		WriteResult writeResult= mongoTemplate.remove(product);
		System.out.println(writeResult);
		int rowsAffected=writeResult.getN();
		if(rowsAffected==0) {
		return false;}
		else
			return true;
	}

	@Override
	public boolean isProductExists(int productId) {

		Product product= mongoTemplate.findById(productId,Product.class,"product");
		if(product== null)
			return false;
		else
			return true;

		
	}

	@Override
	public List<Product> getProducts() {
		int price=1200;
		String searchProduct="Egg";
		Query query=new Query();
		query.addCriteria(Criteria.where("productName").is(searchProduct));
		
	//	BasicQuery query= new BasicQuery("{price}:{$gt :1000}");
		List<Product> allProducts=mongoTemplate.find(query, Product.class);
		System.out.println(allProducts);
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Product.class);
	}

}
