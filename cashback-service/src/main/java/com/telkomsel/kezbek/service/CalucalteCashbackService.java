package com.telkomsel.kezbek.service;

import com.telkomsel.kezbek.request.CashbackRequest;

public interface CalucalteCashbackService {
    
    Double calculateCashbackTier(CashbackRequest cashbackRequest);

    Double calculateCashbackQuantity(CashbackRequest cashbackRequest);
}
