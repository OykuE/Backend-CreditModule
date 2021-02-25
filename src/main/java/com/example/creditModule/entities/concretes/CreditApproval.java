package com.example.creditModule.entities.concretes;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="credit_approvals")
public class CreditApproval {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="credit_approval_id")
	private int id;

	@Column(name="authority_approval")
	boolean authorityApproval;

	@Column(name="need_higher_authority_approval")
	boolean needHigherAuthorityApproval;

	@Column(name="higher_authority_approval")
	boolean higherAuthorityApproval;

	@Column(name="final_credit_approval")
	boolean finalCreditApproval;

	@Column(name="request_needs_update")
	boolean requestNeedsUpdate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credit_request_id", referencedColumnName = "credit_request_id")
	private CreditRequest creditRequest;

}
