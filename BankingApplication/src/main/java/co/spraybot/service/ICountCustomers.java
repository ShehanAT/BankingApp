package co.spraybot.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.spraybot.model.Customer;

@Repository
public interface ICountCustomers {
	@Query("SELECT * FROM public.customers")
	int getCustomerCount();
}
