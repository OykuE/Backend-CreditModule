package com.example.creditModule.api.dto.response;

import lombok.Data;

@Data
public class CreditRequestResponse {
    private double creditAmount;
    private String customerName;
    private String customerSurname;
    private double customerAnnualSalary;
    private double maximumCreditTakenAtOnce;
    private double maximumCreditAmountInAYear;
    private boolean takenCreditThisMonth;
    private boolean customerInBlacklist;
    private boolean customerHasLegalProceeding;
}
