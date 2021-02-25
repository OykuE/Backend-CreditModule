package com.example.creditModule.business.abstracts;

import java.util.List;

import com.example.creditModule.entities.concretes.Customer;

public interface ICustomerService {

		List<Customer> getAll();
		Customer createNewCustomer(Customer customer);

		Customer getCustomerByIdentityNumber(String identityNumber);
		boolean existsCustomerByIdentityNumber(String identityNumber);

		Customer getCustomer(Integer customerId);

	    Customer save(Customer customer);
}
