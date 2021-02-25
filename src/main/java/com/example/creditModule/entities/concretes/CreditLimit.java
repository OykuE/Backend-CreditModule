package com.example.creditModule.entities.concretes;

import javax.persistence.*;

import lombok.Data;

@Data 
@Entity
@Table(name="credit_limits")
public class CreditLimit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="credit_limit_id")
	private int id;

	@Column(name="maximum_credit_taken_at_once")
	private double maximumCreditTakenAtOnce;

	@Column(name="maximum_credit_amount_in_a_year")
	private double maximumCreditAmountInAYear;

	@Column(name="taken_credit_this_month")
    private boolean takenCreditThisMonth;

	@Column(name="customer_in_blacklist")
	private boolean customerInBlacklist;

	@Column(name="customer_has_legal_proceeding")
	private boolean customerHasLegalProceeding;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;




}
