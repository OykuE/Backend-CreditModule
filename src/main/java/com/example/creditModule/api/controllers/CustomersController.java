package com.example.creditModule.api.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.creditModule.api.dto.request.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.creditModule.business.abstracts.ICustomerService;
import com.example.creditModule.entities.concretes.Customer;

@RestController
@RequestMapping("/api/v1")
public class CustomersController {

	@Autowired
	ICustomerService customerService;

	@PostMapping("register")
	public ResponseEntity<?> createCustomer(@RequestBody CustomerForm customerForm) throws ParseException {
		Customer customer = new Customer();
		customer.setCustomerName(customerForm.getCustomerName());
		customer.setCustomerSurname(customerForm.getCustomerSurname());
		customer.setIdentityNumber(customerForm.getIdentityNumber());
		customer.setCustomerAddress(customerForm.getCustomerAddress());
		customer.setCustomerType(customerForm.getCustomerType());
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(customerForm.getDateOfBirth());
		Date dateOfLastCredit = new SimpleDateFormat("dd/MM/yyyy").parse(customerForm.getDateOfLastCredit());

		customer.setDateOfBirth(birthDate);
		customer.setCustomerAnnualSalary(customerForm.getCustomerAnnualSalary());
		customer.setDateOfSubscription(new SimpleDateFormat("dd/MM/yyyy").parse(customerForm.getDateOfSubscription()));

		customer.setDateOfLastCredit(dateOfLastCredit);
		customer.setGsmNumber(customerForm.getGsmNumber());

		customerService.save(customer);

		return ResponseEntity.ok("Customer registration completed");

	}
	
}
