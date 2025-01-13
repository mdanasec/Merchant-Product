package org.jsp.merchantproductapp.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantproductapp.dto.Merchant;
import org.jsp.merchantproductapp.dto.ResponseStructure;
import org.jsp.merchantproductapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MerchantController {
	@Autowired
	private MerchantService merchantService;

	@PostMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant) {
		return merchantService.saveMerchant(merchant);
	}

	@PutMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return merchantService.updateMerchant(merchant);
	}
	
	@GetMapping(value = "/merchants/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable int id){
		return merchantService.findById(id);
	}
	@PostMapping("/merchant/verifyByEmail")
	public ResponseEntity<ResponseStructure<Optional<Merchant>>> verifyByEmail(@RequestParam String email,@RequestParam String password){
		return merchantService.verifyByEmail(email, password);
	}
	
	@GetMapping(value = "/merchants")
    public ResponseEntity<ResponseStructure<List<Merchant>>> findAllMerchants() {
        return merchantService.findAllMerchant();
    }

}
