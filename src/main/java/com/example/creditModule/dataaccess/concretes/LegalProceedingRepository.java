package com.example.creditModule.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditModule.entities.concretes.LegalProceeding;

public interface LegalProceedingRepository extends JpaRepository<LegalProceeding , Integer>{
    boolean existsByCustomerId(Integer customerId);

}
