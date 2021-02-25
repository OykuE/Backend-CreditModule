package com.example.creditModule.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditModule.entities.concretes.Blacklist;

public interface BlacklistRepository extends JpaRepository<Blacklist , Integer>{
    boolean existsByCustomerId(Integer customerId);

}
