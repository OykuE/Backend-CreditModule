package com.example.creditModule.dataaccess.concretes;

import com.example.creditModule.entities.concretes.CreditRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditModule.entities.concretes.CreditApproval;

public interface CreditApprovalRepository extends JpaRepository<CreditApproval , Integer> {
    CreditApproval findByCreditRequest(CreditRequest creditRequest);

}
