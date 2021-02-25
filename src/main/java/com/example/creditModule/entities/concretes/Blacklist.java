package com.example.creditModule.entities.concretes;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="blacklist")
public class Blacklist {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="blacklist_id")
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;

}
