package com.example.creditModule.api.controllers;

import com.example.creditModule.api.dto.response.CreditRequestResponse;
import com.example.creditModule.business.abstracts.ICreditApprovalService;
import com.example.creditModule.business.abstracts.ICreditLimitService;
import com.example.creditModule.business.abstracts.ICreditRequestService;
import com.example.creditModule.business.abstracts.ICustomerService;
import com.example.creditModule.entities.concretes.CreditApproval;
import com.example.creditModule.entities.concretes.CreditLimit;
import com.example.creditModule.entities.concretes.CreditRequest;
import com.example.creditModule.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/authority")
public class AuthorityController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    ICreditLimitService creditLimitService;

    @Autowired
    ICreditRequestService creditRequestService;

    @Autowired
    ICreditApprovalService creditApprovalService;

    @GetMapping("/credit-request/{creditRequestId}")
    public ResponseEntity<CreditRequestResponse> getCreditRequest(@PathVariable Integer creditRequestId){

        CreditRequest creditRequest = creditRequestService.getCreditRequestById(creditRequestId);
        Customer customer = creditRequest.getCustomer();
        CreditLimit creditLimit = creditRequest.getCreditLimit();
        CreditRequestResponse creditRequestResponse = new CreditRequestResponse();

        creditRequestResponse.setCreditAmount(creditRequest.getRequestedAmount());//creditRequest amount
        creditRequestResponse.setCustomerName(customer.getCustomerName());
        creditRequestResponse.setCustomerSurname(customer.getCustomerSurname());
        creditRequestResponse.setCustomerAnnualSalary(customer.getCustomerAnnualSalary());

        creditRequestResponse.setMaximumCreditAmountInAYear(creditLimit.getMaximumCreditAmountInAYear());
        creditRequestResponse.setTakenCreditThisMonth(creditLimit.isTakenCreditThisMonth());
        creditRequestResponse.setMaximumCreditTakenAtOnce(creditLimit.getMaximumCreditTakenAtOnce());
        creditRequestResponse.setCustomerHasLegalProceeding(creditLimit.isCustomerHasLegalProceeding());
        creditRequestResponse.setCustomerInBlacklist(creditLimit.isCustomerInBlacklist());

        return ResponseEntity.ok(creditRequestResponse);
    }


    @PostMapping("/credit-request/{creditRequestId}/approve")
    public ResponseEntity<?> approveCreditRequest(@PathVariable Integer creditRequestId){
        CreditApproval creditApproval = new CreditApproval();
        creditApproval.setFinalCreditApproval(true);
        creditApproval.setAuthorityApproval(true);
        creditApproval.setRequestNeedsUpdate(false);
        creditApproval.setNeedHigherAuthorityApproval(true);
        creditApproval.setRequestNeedsUpdate(false);

        return ResponseEntity.ok("Approved");

    }

    @PostMapping("/credit-request/{creditRequestId}/reject")
    public ResponseEntity<?> rejectCreditRequest(@PathVariable Integer creditRequestId) {
        CreditApproval creditApproval = new CreditApproval();
        CreditRequest creditRequest = creditRequestService.getCreditRequestById(creditRequestId);
        CreditLimit creditLimit = new CreditLimit();

        String rejectionCause = creditApprovalService.checkCreditLimit(creditLimit, creditRequest);

        return ResponseEntity.ok(rejectionCause);
    }



}
