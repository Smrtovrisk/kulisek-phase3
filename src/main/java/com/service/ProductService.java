
 

package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Orders;
import com.bean.Product;
import com.bean.UserProduct;
import com.repository.ProductRepository;

@Service
public class ProductService  {

	@Autowired
	ProductRepository productRepository;
	
	public String storeProduct(Product product) {
		productRepository.save(product);
		return "Product details stored successfully";
	}
	
	public List<Product> findAllProduts() {
		return productRepository.findAll();
	}
	
	   
	
	
	public void decrementQty(int pid) {
		Optional<Product> result = productRepository.findById(pid);
		if(result.isPresent()) {
			Product p = result.get();
			p.setQty(p.getQty()-1);
			productRepository.saveAndFlush(p);
		}
	}
	
	
	 public List<UserProduct> findAllProductsWithUsers() {
	        List<Product> products = productRepository.findAllProductsWithUsers();
	        List<UserProduct> userProducts = new ArrayList<UserProduct>();

	        for (Product product : products) {
	            for (Orders order : product.getListOfOrders()) {
	                UserProduct userProduct = new UserProduct();
	                userProduct.setUserEmail(order.getLogin().getEmailid());
	                userProduct.setProductId(product.getPid());
	                userProduct.setProductName(product.getPname());
	                userProduct.setPrice(product.getPrice());

	                userProducts.add(userProduct);
	            }
	        }

	        return userProducts;
	    }
	
}