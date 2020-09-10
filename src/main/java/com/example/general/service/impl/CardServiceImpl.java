package com.example.general.service.impl;


import com.example.general.model.Card;
import com.example.general.model.DTO.PaymentDto;
import com.example.general.repository.CardRepository;
import com.example.general.service.abst.CardService;
import com.example.general.util.exception.NoEnoughBalanceToFulfillException;
import com.example.general.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;


@Service
public class CardServiceImpl implements CardService {


    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional
    public void charge(int id,double sum) throws NotFoundException, NoEnoughBalanceToFulfillException {
        Card card = cardRepository.getByConsumer_id(id);
        NotFoundException.check(card == null, "no.card");
        NoEnoughBalanceToFulfillException.check(card.getBalance() < sum, "no.enough.money");
        card.setBalance(card.getBalance() - sum);
        cardRepository.save(card);

    }


    @Override
    @Transactional
    public void refund(int id,double sum) throws NotFoundException {
        Card card = cardRepository.getByConsumer_id(id);
        NotFoundException.check(card == null, "no.card");
        card.setBalance(card.getBalance() + sum);
        cardRepository.save(card);

    }
}
