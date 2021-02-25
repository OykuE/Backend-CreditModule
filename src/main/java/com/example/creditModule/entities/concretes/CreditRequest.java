package com.example.creditModule.entities.concretes;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data 
@Entity
@Table(name="credit_requests")
public class CreditRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="credit_request_id")
	private int id;

	@Column(name="approval_status")
	private String approvalStatus;

	@Column(name="rejection_cause")
	private String rejectionCause;

	@Column(name="request_amount")
	private double requestedAmount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonBackReference
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credit_limit_id", referencedColumnName = "credit_limit_id")
	private CreditLimit creditLimit;

}
