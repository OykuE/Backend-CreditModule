package com.example.creditModule.business.concretes;

import com.example.creditModule.business.abstracts.ICreditApprovalService;
import com.example.creditModule.dataaccess.concretes.CreditApprovalRepository;
import com.example.creditModule.entities.concretes.CreditApproval;
import com.example.creditModule.entities.concretes.CreditLimit;
import com.example.creditModule.entities.concretes.CreditRequest;
import com.example.creditModule.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditApprovalManager implements ICreditApprovalService {
    @Autowired
    CreditApprovalRepository creditApprovalRepository;

    @Autowired
    CreditLimitManager creditLimitManager;

    @Autowired
   CustomerManager customerManager;


    @Override
    public CreditApproval setAuthorityApproval(CreditApproval creditApproval, boolean authorityApprovalState, boolean requestNeedsUpdate) {
        creditApproval.setRequestNeedsUpdate(requestNeedsUpdate);
        creditApproval.setAuthorityApproval(authorityApprovalState);
       return creditApprovalRepository.save(creditApproval);
    }

    @Override
    public CreditApproval setNeedHigherAuthorityApproval(CreditApproval creditApproval, boolean needHigherAuthorityApprovalState) {
        creditApproval.setNeedHigherAuthorityApproval(needHigherAuthorityApprovalState);
        return creditApprovalRepository.save(creditApproval);
    }

    @Override
    public CreditApproval setHigherAuthorityApproval(CreditApproval creditApproval, boolean higherAuthorityApprovalState) {
        if(creditApproval.isNeedHigherAuthorityApproval()) {
            creditApproval.setHigherAuthorityApproval(higherAuthorityApprovalState);
            return creditApprovalRepository.save(creditApproval);
        }
        else
            return null;
    }

    @Override
    public CreditApproval setFinalCreditApproval(CreditApproval creditApproval, CreditRequest creditRequest, String rejectionCause) {
        if(creditApproval.isNeedHigherAuthorityApproval() && creditApproval.isHigherAuthorityApproval())  {
            creditApproval.setFinalCreditApproval(true);
            creditRequest.setApprovalStatus("approved");
        }
        else if(!creditApproval.isNeedHigherAuthorityApproval() && creditApproval.isAuthorityApproval()){
            creditApproval.setFinalCreditApproval(true);
            creditRequest.setApprovalStatus("approved");
        }
        else{
            creditApproval.setFinalCreditApproval(false);
            creditRequest.setRejectionCause(rejectionCause);
            creditRequest.setApprovalStatus("rejected");

        }

        return creditApprovalRepository.save(creditApproval);
    }

    @Override
    public CreditApproval getCreditApprovalWithCreditRequest(CreditRequest creditRequest) {
        return creditApprovalRepository.findByCreditRequest(creditRequest);
    }

    @Override
    public String checkCreditLimit(CreditLimit creditLimit, CreditRequest creditRequest) {
        Customer customer = creditRequest.getCustomer();
        creditLimit = creditLimitManager.calculateMaximumCreditAmountInAYear(creditLimit,customer);
        if(creditLimit.isCustomerInBlacklist())
            return "customer is in blacklist";
        else if(creditLimit.isCustomerHasLegalProceeding())
            return "customer has legal proceeding";
        else if(creditLimit.isTakenCreditThisMonth())
            return "credit has been already taken in this month";
        else if(creditLimit.getMaximumCreditTakenAtOnce()<creditRequest.getRequestedAmount())
            return "credit limit taken at once is exceeded";
       else if(creditLimit.getMaximumCreditAmountInAYear()<customer.getTotalCreditInAYear()+creditRequest.getRequestedAmount())
           return "credit limit taken in year is exceeded \n" +
                   "Suggested max credit amount " + (creditLimit.getMaximumCreditAmountInAYear() -  customer.getTotalCreditInAYear());
       else
           return "Credit request is rejected. Rejection cause is not mentioned.";

    }

}
