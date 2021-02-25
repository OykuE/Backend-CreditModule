package com.example.creditModule.business.abstracts;

import com.example.creditModule.dataaccess.concretes.BlacklistRepository;
import com.example.creditModule.entities.concretes.Blacklist;
import com.example.creditModule.entities.concretes.CreditLimit;
import com.example.creditModule.entities.concretes.Customer;
import com.example.creditModule.entities.concretes.LegalProceeding;

import java.util.List;

public interface ICreditLimitService {
    public List<CreditLimit> getAll();
    CreditLimit checkBlacklist(CreditLimit creditLimit,Customer customer);
    CreditLimit checkLegalProceeding(CreditLimit creditLimit, Customer customer);
    CreditLimit checkMonthlyCredit(CreditLimit creditLimit,Customer customer);
    CreditLimit calculateMaximumCreditTakenAtOnce(CreditLimit creditLimit,Customer customer);
    CreditLimit calculateMaximumCreditAmountInAYear(CreditLimit creditLimit,Customer customer);
    CreditLimit addCreditLimit(CreditLimit creditLimit);
}
