package com.example.creditModule.business.abstracts;

public interface IBlacklistService {
    boolean existByCustomerId(Integer customerId);
}
