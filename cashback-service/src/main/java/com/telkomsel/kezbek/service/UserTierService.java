package com.telkomsel.kezbek.service;

import com.telkomsel.kezbek.model.UserTier;

public interface UserTierService {
    

    public UserTier processUserTier(String email, String partnerId);
}
