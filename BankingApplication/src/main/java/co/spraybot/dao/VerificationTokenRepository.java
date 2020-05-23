package co.spraybot.dao;

import java.sql.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.spraybot.model.Customer;
import co.spraybot.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
	VerificationToken findByToken(String token);
	
	VerificationToken findByCustomer(Customer customer);
	
	Stream<VerificationToken> findAllByExpiryDateLessThan(Date now);
	
	void deleteByExpiryDateLessThan(Date now);
	
	@Modifying
	@Query("delete from VerificationToken t where t.expiryDate <= ?1")
	void deleteAllExpiredSince(Date now);
	
}
