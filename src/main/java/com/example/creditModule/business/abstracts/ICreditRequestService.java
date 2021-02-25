package com.example.creditModule.business.abstracts;

import com.example.creditModule.entities.concretes.CreditApproval;
import com.example.creditModule.entities.concretes.CreditRequest;

import java.util.List;

public interface ICreditRequestService {
    List<CreditRequest> getAll();
    CreditRequest addCreditRequest(CreditRequest creditRequest);
    CreditRequest updateCreditRequest(CreditRequest creditRequest, CreditApproval creditApproval, double requestAmount);
    void cancelCreditRequest(CreditRequest creditRequest);
    void sellCredit(CreditRequest creditRequest);
    CreditRequest getCreditRequestById(Integer creditRequestId);
    CreditRequest addCreditRequestWithAmount(Double amount, Integer customerId);
}
