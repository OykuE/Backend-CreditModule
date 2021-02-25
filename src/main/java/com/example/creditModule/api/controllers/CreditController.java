package com.example.creditModule.api.controllers;

import com.example.creditModule.api.dto.request.CreditForm;
import com.example.creditModule.business.abstracts.ICreditApprovalService;
import com.example.creditModule.business.abstracts.ICreditRequestService;
import com.example.creditModule.business.abstracts.ICustomerService;
import com.example.creditModule.entities.concretes.CreditApproval;
import com.example.creditModule.entities.concretes.CreditRequest;
import com.example.creditModule.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credit")
public class CreditController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    ICreditRequestService creditRequestService;

    @Autowired
    ICreditApprovalService creditApprovalService;

    @PostMapping("/credit-request/{identityNumber}")
    public ResponseEntity<?> makeCreditRequest(@PathVariable String identityNumber, @RequestBody CreditForm creditForm) {
        if (customerService.existsCustomerByIdentityNumber(identityNumber)) {
            Customer customer = customerService.getCustomerByIdentityNumber(identityNumber);
            CreditRequest creditRequest = creditRequestService.addCreditRequestWithAmount(creditForm.getCreditAmount(), customer.getId());
            creditRequestService.addCreditRequest(creditRequest);
            return ResponseEntity.ok("credit request is created");
        } else {
            return new ResponseEntity<>("you must register before credit request", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/credit-request/{customerId}/{creditRequestId}/update")
    public ResponseEntity<?> updateCreditRequest(@PathVariable Integer customerId, @PathVariable Integer creditRequestId, @RequestBody Double creditAmount) {
        CreditRequest creditRequest = creditRequestService.getCreditRequestById(creditRequestId);
        if (creditRequest.getCustomer().getId() == customerId) {
            CreditApproval creditApproval = creditApprovalService.getCreditApprovalWithCreditRequest(creditRequest);
            CreditRequest updatedCreditRequest = creditRequestService.updateCreditRequest(creditRequest, creditApproval, creditAmount);
            creditRequestService.addCreditRequest(updatedCreditRequest);
            return ResponseEntity.ok("credit request is updated");
        } else {
            return new ResponseEntity<>("This credit request is not yours!", HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/credit-request/{customerId}/{creditRequestId}/cancel")
    public ResponseEntity<?> cancelCreditRequest(@PathVariable Integer customerId, @PathVariable Integer creditRequestId) {
        CreditRequest creditRequest = creditRequestService.getCreditRequestById(creditRequestId);
        if (creditRequest.getCustomer().getId() == customerId) {
            creditRequestService.cancelCreditRequest(creditRequest);
            return ResponseEntity.ok("credit request is cancelled");
        } else {
            return new ResponseEntity<>("This credit request is not yours!", HttpStatus.UNAUTHORIZED);
        }
    }
}
