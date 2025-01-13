package org.jsp.merchantproductapp.repository;

import java.util.Optional;

import org.jsp.merchantproductapp.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	
	@Query("SELECT m FROM Merchant m WHERE m.email=?1 AND m.password=?2")
	public Optional<Merchant> verifyByEmail(String email, String password);
}
