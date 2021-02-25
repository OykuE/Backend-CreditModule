package com.example.creditModule.business.abstracts;

import com.example.creditModule.entities.concretes.Credit;
import com.example.creditModule.entities.concretes.CreditApproval;

public interface ICreditService {
	Credit addCredit(Credit credit, CreditApproval creditApproval);
}
