package com.spring.sqldemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sqldemo.customexception.BusinessException;
import com.spring.sqldemo.customexception.InputException;
import com.spring.sqldemo.dto.ProductAndReview;
import com.spring.sqldemo.entity.Product;
import com.spring.sqldemo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ProductRepository repo;
	
	@Transactional
	public List<Product> saveProduct(List<ProductAndReview> products) throws Exception {
		
		try {
		   return products.stream().map(product->{
			Product saveProduct = new Product(product.getName(), product.getPrice(),
					product.getCategory(),product.getTags(),product.getReviews());
			return repo.save(saveProduct);
		}).collect(Collectors.toList());
		
		}catch(IllegalArgumentException e) {
			throw new InputException("602", "The product List is empty "+e.getMessage());
		}catch(Exception e) {
			throw new BusinessException("603", "Something went wrong in servicelayer while saving the product list "+e.getMessage());
		}
	}
	
	public Product getProductById(Long id) {
	    try {
	        Optional<Product> product = repo.findById(id);
	        if (product.isPresent()) {
	            return product.get();
	        } else {
	            throw new BusinessException("604","Product not found with id: " + id);
	        }
	    }  catch (IllegalArgumentException e) {
	        throw new BusinessException("605","Given id is null, send valid id "+e.getMessage());
	    }  
	    catch (Exception e) {
	        throw new BusinessException("606","Something went wrong in the servicelayer while fetching the product "+e.getMessage());
	    }
	}

	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<>();
		try {
		products = repo.findAll();
		if(products.isEmpty()) {
			throw new BusinessException("607", "Product list is empty, no data in db ");
		}
		return products;
		}catch(Exception e) {
			throw new BusinessException("608", "Something went wrong in servicelayer while fetching the productlist "+e.getMessage());
		}
	}

	public Boolean updatePrice(String name, double price) {
		try {
		int updateCount = repo.updatePrice(name, price);
		Boolean result = false;
		if(updateCount>0) {
			result = true;
		}else {
			throw new BusinessException("609", "price update failed ");
		}
		return result;
		}catch(Exception e) {
			throw new BusinessException("610","Something went wrong in servicelayer while updating the price "+e.getMessage());
		}
	}

	public void deleteProductById(Long id) {
		try {
			if(repo.existsById(id)) {
				repo.deleteById(id);
			}else {
				throw new BusinessException("611", "No product available in this id "+id);
			}
		}catch(Exception e) {
			throw new BusinessException("612","Something went wrong in servicelayer while deleting the product "+e.getMessage());
		}
	}
}
