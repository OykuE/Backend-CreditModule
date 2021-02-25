package com.example.creditModule.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditModule.entities.concretes.CreditRequest;

public interface CreditRequestRepository extends JpaRepository<CreditRequest , Integer>{

}
