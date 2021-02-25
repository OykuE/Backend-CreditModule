package com.example.creditModule.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditModule.entities.concretes.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    boolean existsByIdentityNumber(String identityNumber);

    Customer findByIdentityNumber(String identityNumber);

}
