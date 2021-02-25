package com.example.creditModule.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.creditModule.business.abstracts.ICustomerService;
import com.example.creditModule.dataaccess.concretes.CustomerRepository;
import com.example.creditModule.entities.concretes.Customer;

@Service
public class CustomerManager implements ICustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(Integer customerId){
		return customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer createNewCustomer(Customer customer) {
		if(customerRepository.existsByIdentityNumber(customer.getIdentityNumber()))
			return null;
		else
			return customerRepository.save(customer);


	}

	@Override
	public Customer getCustomerByIdentityNumber(String identityNumber) {
		return customerRepository.findByIdentityNumber(identityNumber);
	}

	@Override
	public boolean existsCustomerByIdentityNumber(String identityNumber) {
		return customerRepository.existsByIdentityNumber(identityNumber);
	}


}
