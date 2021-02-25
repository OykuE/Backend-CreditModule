package com.example.creditModule.entities.concretes;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="legal_proceedings")
public class LegalProceeding {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="legal_proceeding_id")
	private int id;

	@Column(name ="legal_proceeding_date", columnDefinition = "timestamp without time zone not null")
	@NotNull
	private Date legalProceedingDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonBackReference
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credit_id", referencedColumnName = "credit_id")
	private Credit credit;

}
