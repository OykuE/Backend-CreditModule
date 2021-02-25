package com.example.creditModule.business.concretes;

import com.example.creditModule.business.abstracts.ICreditLimitService;
import com.example.creditModule.business.abstracts.ICreditRequestService;
import com.example.creditModule.business.abstracts.ICustomerService;
import com.example.creditModule.dataaccess.concretes.CreditRequestRepository;
import com.example.creditModule.entities.concretes.CreditApproval;
import com.example.creditModule.entities.concretes.CreditLimit;
import com.example.creditModule.entities.concretes.CreditRequest;
import com.example.creditModule.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreditRequestManager implements ICreditRequestService {
    @Autowired
    CreditRequestRepository creditRequestRepository;

    @Autowired
    ICustomerService customerService;

    @Autowired
    ICreditLimitService creditLimitService;

    @Override
    public List<CreditRequest> getAll() {
        return creditRequestRepository.findAll();
    }

    @Override
    public CreditRequest addCreditRequest(CreditRequest creditRequest) {
        return creditRequestRepository.save(creditRequest);
    }

    @Override
    public CreditRequest updateCreditRequest(CreditRequest creditRequest, CreditApproval creditApproval, double requestAmount) {
       if(creditApproval.isRequestNeedsUpdate()){
           creditRequest.setRequestedAmount(requestAmount);
       }
       return creditRequestRepository.save(creditRequest);
    }

    @Override
    public void cancelCreditRequest(CreditRequest creditRequest) {
        creditRequestRepository.delete(creditRequest);
    }

    @Override
    public void sellCredit(CreditRequest creditRequest) {
        creditRequestRepository.delete(creditRequest);
    }

    @Override
    public CreditRequest getCreditRequestById(Integer creditRequestId) {
        return creditRequestRepository.findById(creditRequestId).orElseThrow(() -> new RuntimeException("Credit request could not find"));
    }

    @Override
    public CreditRequest addCreditRequestWithAmount(Double amount, Integer customerId) {
        CreditRequest creditRequest = new CreditRequest();
        Customer customer = customerService.getCustomer(customerId);
        creditRequest.setRequestedAmount(amount);
        creditRequest.setApprovalStatus("processing");
        creditRequest.setCustomer(customer);
        creditRequest.setRejectionCause("");

        CreditLimit creditLimit = new CreditLimit();
        creditLimit.setCustomer(customer);
        creditLimitService.calculateMaximumCreditAmountInAYear(creditLimit,customer);
        creditLimitService.calculateMaximumCreditTakenAtOnce(creditLimit,customer);
        creditLimitService.checkMonthlyCredit(creditLimit,customer);
        creditLimitService.checkBlacklist(creditLimit,customer);
        creditLimitService.checkLegalProceeding(creditLimit,customer);
        creditRequest.setCreditLimit(creditLimit);
        return creditRequest;
    }
}
