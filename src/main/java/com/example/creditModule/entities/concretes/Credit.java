package com.example.creditModule.entities.concretes;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="credits")
public class Credit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="credit_id")
	private int id;

	@Column(name="credit_amount")
	private double creditAmount;

	@Column(name ="credit_date", columnDefinition = "timestamp without time zone not null")
	@NotNull
	private Date creditDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonBackReference
	private Customer customer;
}
