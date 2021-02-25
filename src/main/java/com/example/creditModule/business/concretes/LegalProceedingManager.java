package com.example.creditModule.business.concretes;

import com.example.creditModule.business.abstracts.ILegalProceedingService;
import com.example.creditModule.dataaccess.concretes.LegalProceedingRepository;
import com.example.creditModule.entities.concretes.LegalProceeding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalProceedingManager implements ILegalProceedingService {
    @Autowired
    LegalProceedingRepository legalProceedingRepository;

    public boolean existByCustomerId(Integer customerId) {
        return legalProceedingRepository.existsByCustomerId(customerId);
    }
}
