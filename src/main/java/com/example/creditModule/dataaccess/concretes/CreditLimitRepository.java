package com.example.creditModule.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditModule.entities.concretes.CreditLimit;

public interface CreditLimitRepository extends JpaRepository<CreditLimit , Integer>{

}
