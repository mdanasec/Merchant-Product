package org.jsp.merchantproductapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantproductapp.dao.MerchantDao;
import org.jsp.merchantproductapp.dao.ProductDao;
import org.jsp.merchantproductapp.dto.Merchant;
import org.jsp.merchantproductapp.dto.Product;
import org.jsp.merchantproductapp.dto.ResponseStructure;
import org.jsp.merchantproductapp.exception.MerchantNotFoundException;
import org.jsp.merchantproductapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Merchant> dBMerchant = merchantDao.findById(merchant_id);
		if (dBMerchant.isPresent()) {
			Merchant merchant = dBMerchant.get();
			merchant.getProducts().add(product);
			product.setMerchant(merchant);
			merchantDao.saveMerchant(merchant);
			structure.setData(productDao.saveProduct(product));
			structure.setMessage("Product added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new MerchantNotFoundException();
	}

	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product ,int id) {
	    ResponseStructure<Product> structure = new ResponseStructure<>();

	    Optional<Product> recProduct = productDao.findById(product.getId());

	    if (recProduct.isPresent()) {
	        Product existingProduct = recProduct.get();

	        // Update the fields of the existing product with the new values
	        existingProduct.setName(product.getName());
	        existingProduct.setBrand(product.getBrand());
	        existingProduct.setCategory(product.getCategory());
	        existingProduct.setDescription(product.getDescription());
	        existingProduct.setImage_url(product.getImage_url());
	        existingProduct.setCost(product.getCost());

	        // Save the updated product
	        Product updateProduct = productDao.saveProduct(existingProduct);

	        structure.setData(updateProduct);
	        structure.setMessage("Product Updated Successfully");
	        structure.setStatusCode(HttpStatus.ACCEPTED.value());
	        return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
	    } 
	        // Product with the given ID not found
	        throw new ProductNotFoundException("product not updated");
	}
	
	public ResponseEntity<ResponseStructure<Product>> findProductById(int id){
		ResponseStructure<Product> structure= new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findProductById(id);
		if(recProduct.isPresent()) {
			structure.setMessage("Product Found");
			structure.setData(recProduct.get());
			structure.setStatusCode(HttpStatus.OK.value());
			 return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("product not found ");
	}
	
	
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct(){
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> recProduct = productDao.findAllProduct();
		if(!recProduct.isEmpty()) {
			structure.setMessage("product found ");
			structure.setData(recProduct);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>> (structure , HttpStatus.OK);
		}
		throw new ProductNotFoundException("Product Not found");
	}
	
	
	public ResponseEntity<ResponseStructure<String>> deleteProductById(int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> recProduct=productDao.findById(id);
		
		if(recProduct.isPresent()) {
			productDao.deleteProductById(id);
			
			structure.setMessage("product deleted");
			structure.setData("product found");
			structure.setStatusCode(HttpStatus.NO_CONTENT.value());
			return new  ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NO_CONTENT);
		}
		throw new ProductNotFoundException("id not found ");
	}
	
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand){
		ResponseStructure<List<Product>> structure= new ResponseStructure<>();
		List<Product> products = productDao.findByBrand(brand);
		if(products.size()>0) {
			structure.setData(products);
			structure.setMessage("product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("you have entered invalid brand");
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category){
		ResponseStructure<List<Product>> structure= new ResponseStructure<>();
		List<Product> products = productDao.findByCategory(category);
		if(products.size()>0) {
			structure.setData(products);
			structure.setMessage("product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("you have entered invalid category");
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int id){
		ResponseStructure<List<Product>> structure= new ResponseStructure<>();
		List<Product> products = productDao.findByMerchantId(id);
		if(products.size()>0) {
			structure.setData(products);
			structure.setMessage("product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("you have entered invalid id");
	}
	

	
}
