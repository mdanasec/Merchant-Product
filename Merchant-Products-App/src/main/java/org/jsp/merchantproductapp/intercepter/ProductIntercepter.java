package org.jsp.merchantproductapp.intercepter;

import java.util.Optional;

import org.jsp.merchantproductapp.dao.ProductDao;
import org.jsp.merchantproductapp.dto.Product;
import org.jsp.merchantproductapp.dto.ResponseStructure;
import org.jsp.merchantproductapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;


public class ProductIntercepter implements HandlerInterceptor {
	
	@Autowired
	private ProductDao productDao;
	
	public ResponseEntity<ResponseStructure<Product>> findProductById(int id){
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct= productDao.findProductById(id);
		if(recProduct.isPresent()) {
			structure.setMessage("Found Product");
			structure.setData(recProduct.get());
			structure.setStatusCode(HttpStatus.OK.value());
			 return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new  ProductNotFoundException("product not found ");
	}
}
