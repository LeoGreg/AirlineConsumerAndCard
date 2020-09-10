package com.example.general.controller;

import com.example.general.service.abst.CardService;
import com.example.general.util.exception.NoEnoughBalanceToFulfillException;
import com.example.general.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/charge")
    public ResponseEntity charge(@RequestParam int id, @RequestParam double sum) throws NotFoundException, NoEnoughBalanceToFulfillException {
        cardService.charge(id, sum);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refund")
    public ResponseEntity refund(@RequestParam int id, @RequestParam double sum) throws NotFoundException, NoEnoughBalanceToFulfillException {
        cardService.refund(id, sum);
        return ResponseEntity.noContent().build();
    }
}
