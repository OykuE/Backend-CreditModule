package com.example.creditModule.api.dto.request;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import java.util.Date;
import lombok.Data;

@Data
public class CustomerForm {

    private String customerType;
    private String identityNumber;
    private String customerName;
    private String customerSurname;
    private String dateOfBirth;
    private String dateOfSubscription;
    private double customerAnnualSalary;
    private String customerAddress;
    private String gsmNumber;
    private String dateOfLastCredit;

}
