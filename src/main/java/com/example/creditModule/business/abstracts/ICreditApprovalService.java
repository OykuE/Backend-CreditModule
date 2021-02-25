package com.example.creditModule.business.abstracts;

import com.example.creditModule.entities.concretes.CreditApproval;
import com.example.creditModule.entities.concretes.CreditLimit;
import com.example.creditModule.entities.concretes.CreditRequest;

public interface ICreditApprovalService {
    CreditApproval setAuthorityApproval(CreditApproval creditApproval, boolean authorityApprovalState,boolean requestNeedsUpdate);
    CreditApproval setHigherAuthorityApproval(CreditApproval creditApproval,boolean higherAuthorityApprovalState);
    CreditApproval setNeedHigherAuthorityApproval(CreditApproval creditApproval,boolean needHigherAuthorityApprovalState);
    CreditApproval setFinalCreditApproval(CreditApproval creditApproval, CreditRequest creditRequest, String rejectionCause);
    CreditApproval getCreditApprovalWithCreditRequest(CreditRequest creditRequest);
    String checkCreditLimit(CreditLimit creditLimit,CreditRequest creditRequest);
}
