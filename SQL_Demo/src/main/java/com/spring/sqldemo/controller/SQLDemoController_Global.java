package com.spring.sqldemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sqldemo.customexception.BusinessException;
import com.spring.sqldemo.customexception.ControllerException;
import com.spring.sqldemo.dto.ProductAndReview;
import com.spring.sqldemo.entity.Product;
import com.spring.sqldemo.service.ProductService;

@RestController
@RequestMapping("/globalproduct")
public class SQLDemoController_Global {
	
	Logger logger = LoggerFactory.getLogger(SQLDemoController_Global.class);

	@Autowired
	ProductService productService;
	
	@PostMapping("/saveProduct")
	public ResponseEntity<?> saveProductAndReview(@RequestBody List<ProductAndReview> products) throws Exception{
			productService.saveProduct(products);
			return new ResponseEntity<String>("Saved Successfully ", HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") Long id){

			Product product = new Product();
			product = productService.getProductById(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);

	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllProducts(){
		try {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("615","Something went wrong in controller layer");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/updateprice")
	public ResponseEntity<?> updatePrice(@RequestBody Product product){
		try {
		Boolean result = productService.updatePrice(product.getName(), product.getPrice());
		return new ResponseEntity<Boolean>(result, HttpStatus.ACCEPTED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("615","Something went wrong in controller layer");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id){
		try {
		productService.deleteProductById(id);
		return new ResponseEntity<String>("Products deleted successfully ", HttpStatus.OK);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("615","Something went wrong in controller layer");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
