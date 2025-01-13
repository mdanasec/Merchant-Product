package org.jsp.merchantproductapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantproductapp.dao.MerchantDao;
import org.jsp.merchantproductapp.dto.Merchant;
import org.jsp.merchantproductapp.dto.ResponseStructure;
import org.jsp.merchantproductapp.exception.MerchantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setData(merchantDao.saveMerchant(merchant));
		structure.setMessage("Merchant Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(merchant.getId());
		if (recMerchant.isPresent()) {
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setPhone(merchant.getPhone());
			structure.setData(merchantDao.saveMerchant(dbMerchant));
			structure.setMessage("Merchant Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
		}
		throw new MerchantNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found ");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Optional<Merchant>>> verifyByEmail(String email, String password){
		ResponseStructure<Optional<Merchant>> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verifyByEmail(email, password);
		if(recMerchant.isPresent()) {
			structure.setMessage("Merchnat verify Sucessfully ");
			structure.setData(merchantDao.verifyByEmail(email, password));
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Optional<Merchant>>> (structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException();
	}
	
	
	public ResponseEntity<ResponseStructure<List<Merchant>>> findAllMerchant() {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		List<Merchant> recMerchant = merchantDao.findAllMerchant();

		if (!recMerchant.isEmpty()) {
			structure.setMessage("Merchants Found");
			structure.setData(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.OK);
		}

		throw new MerchantNotFoundException();
	}

}
