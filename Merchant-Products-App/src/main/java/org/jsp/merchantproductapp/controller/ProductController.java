package org.jsp.merchantproductapp.controller;

import java.util.List;

import org.jsp.merchantproductapp.dto.Product;
import org.jsp.merchantproductapp.dto.ResponseStructure;
import org.jsp.merchantproductapp.exception.ProductNotFoundException;
import org.jsp.merchantproductapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/products/{merchant_id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,
			@PathVariable int merchant_id) {
		return productService.saveProduct(product, merchant_id);
	}
	
	@PutMapping("/updateproud/{id}") 
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product, @PathVariable int id) {
		return productService.updateProduct(product, id);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int id){
		return productService.findProductById(id);
	}
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct(){
		return productService.findAllProduct();
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProductById(@PathVariable int id){
		return productService.deleteProductById(id);
	}

	@GetMapping(value = "/product/by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable String brand) {
		return productService.findByBrand(brand);
	}
	
	@GetMapping(value = "/product/by-category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable String category) {
		return productService.findByCategory(category);
	}
	
	@GetMapping(value = "/product/by-merchantid/{id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable int id) {
		return productService.findByMerchantId(id);
	}
	
}