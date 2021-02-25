package com.example.creditModule.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditModule.entities.concretes.Credit;

public interface CreditRepository extends JpaRepository<Credit , Integer>{

}
