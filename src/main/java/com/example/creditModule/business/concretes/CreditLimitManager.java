package com.example.creditModule.business.concretes;

import com.example.creditModule.business.abstracts.ICreditLimitService;
import com.example.creditModule.dataaccess.concretes.CreditLimitRepository;
import com.example.creditModule.entities.concretes.Blacklist;
import com.example.creditModule.entities.concretes.CreditLimit;

import com.example.creditModule.entities.concretes.Customer;
import com.example.creditModule.entities.concretes.LegalProceeding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;


import java.util.List;
@Service
public class CreditLimitManager implements ICreditLimitService {
    @Autowired
    CreditLimitRepository creditLimitRepository;
    @Autowired
    BlacklistManager blacklistManager;
    @Autowired
    LegalProceedingManager legalProceedingManager;


    public List<CreditLimit> getAll() {
        return creditLimitRepository.findAll();
    }

    @Override
    public CreditLimit checkBlacklist(CreditLimit creditLimit,Customer customer) {
        creditLimit.setCustomerInBlacklist(blacklistManager.existByCustomerId(customer.getId()));
           return creditLimitRepository.save(creditLimit);
    }

    @Override
    public CreditLimit checkLegalProceeding(CreditLimit creditLimit, Customer customer) {
        creditLimit.setCustomerHasLegalProceeding(legalProceedingManager.existByCustomerId(customer.getId()));
        return creditLimitRepository.save(creditLimit);
    }

    @Override
    public CreditLimit checkMonthlyCredit(CreditLimit creditLimit, Customer customer) {
        Date now = new Date();
        Date lastCreditDate = customer.getDateOfLastCredit();
        long howManyDaysAgo = ((now.getTime() - lastCreditDate.getTime())/(1000 * 60 * 60 * 24));
        creditLimit.setTakenCreditThisMonth((howManyDaysAgo) < 30);
        return creditLimitRepository.save(creditLimit);
    }

        @Override
    public CreditLimit calculateMaximumCreditTakenAtOnce(CreditLimit creditLimit, Customer customer) {
            Date now = new Date();
            Date subscriptionDate = customer.getDateOfSubscription();
            long howManyDaysAgo = ((now.getTime() - subscriptionDate.getTime())/(1000 * 60 * 60 * 24));
            if(howManyDaysAgo>360)
                creditLimit.setMaximumCreditTakenAtOnce(customer.getCustomerAnnualSalary() * 2 );
            else
                creditLimit.setMaximumCreditTakenAtOnce(customer.getCustomerAnnualSalary() );

         return creditLimitRepository.save(creditLimit);
    }

    @Override
    public CreditLimit calculateMaximumCreditAmountInAYear(CreditLimit creditLimit, Customer customer) {
        creditLimit.setMaximumCreditAmountInAYear(customer.getCustomerAnnualSalary() * 24 ) ;
        return creditLimitRepository.save(creditLimit);
    }

    @Override
    public CreditLimit addCreditLimit(CreditLimit creditLimit) {
        return creditLimitRepository.save(creditLimit);
    }


}
