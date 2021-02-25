package com.example.creditModule.business.concretes;

import com.example.creditModule.business.abstracts.ICreditApprovalService;
import com.example.creditModule.entities.concretes.CreditApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.creditModule.business.abstracts.ICreditService;
import com.example.creditModule.dataaccess.concretes.CreditRepository;
import com.example.creditModule.entities.concretes.Credit;

@Service
public class CreditManager implements ICreditService {
	@Autowired
	CreditRepository creditRepository;

	@Override
	public Credit addCredit(Credit credit, CreditApproval creditApproval) {
		if(creditApproval.isFinalCreditApproval())
			return creditRepository.save(credit);
		else return null;
	}



}
