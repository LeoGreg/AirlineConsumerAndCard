package com.example.general.service.abst;



import com.example.general.model.DTO.PaymentDto;
import com.example.general.util.exception.NoEnoughBalanceToFulfillException;
import com.example.general.util.exception.NotFoundException;

import javax.transaction.Transactional;

public interface CardService {


    @Transactional
    void charge(int id, double sum) throws NotFoundException, NoEnoughBalanceToFulfillException;

    @Transactional
    void refund(int id, double sum) throws NotFoundException;
}
