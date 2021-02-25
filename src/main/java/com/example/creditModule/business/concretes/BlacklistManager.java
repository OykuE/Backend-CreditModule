package com.example.creditModule.business.concretes;

import com.example.creditModule.business.abstracts.IBlacklistService;
import com.example.creditModule.dataaccess.concretes.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacklistManager implements IBlacklistService {
    @Autowired
    BlacklistRepository blacklistRepository;

    public boolean existByCustomerId(Integer customerId){
        return blacklistRepository.existsByCustomerId(customerId);
    }

}
