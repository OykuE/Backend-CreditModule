package com.example.creditModule.entities.concretes;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="customers")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_id")
	private int id;

	@Column(name="customer_type")
	private String customerType;

	@Column(name="identity_number")
	private String identityNumber;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="customer_surname")
	private String customerSurname;

	@Column(name ="date_of_birth", columnDefinition = "timestamp without time zone not null")
	@NotNull
	private Date dateOfBirth;

	@Column(name ="date_of_subscription", columnDefinition = "timestamp without time zone not null")
	@NotNull
	private Date dateOfSubscription;

	@Column(name ="date_of_last_credit", columnDefinition = "timestamp without time zone not null")
	@NotNull
	private Date dateOfLastCredit;

	@Column(name="customer_annual_salary")
	private double customerAnnualSalary;

	@Column(name="customer_address")
	private String customerAddress;

	@Column(name="gsm_number")
	private String gsmNumber;

	@Column(name="total_credit_in_a_year")
	private double totalCreditInAYear;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credit_limit_id", referencedColumnName = "credit_limit_id")
	private CreditLimit creditLimit;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Credit> credit= new HashSet<>();

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<LegalProceeding> legalProceedings= new HashSet<>();

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<CreditRequest> creditRequest= new HashSet<>();


}
