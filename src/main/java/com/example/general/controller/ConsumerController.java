package com.example.general.controller;


import com.example.general.model.DTO.ConsumerCardDTO;
import com.example.general.model.DTO.VerifyDTO;
import com.example.general.service.abst.ConsumerService;
import com.example.general.util.exception.DuplicateException;
import com.example.general.util.exception.NotFoundException;
import com.example.general.util.exception.UnverifiedException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody ConsumerCardDTO consumer) throws DuplicateException {
        log.info(">>>register method:");
        consumerService.register(consumer);
        log.info(">>>reg is done:");
        return ResponseEntity.ok(consumer);
    }

    @PostMapping("/verify")
    public ResponseEntity verify(@Valid @RequestBody VerifyDTO u) throws NotFoundException, UnverifiedException {
        consumerService.verify(u.getUsername(), u.getCode());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/item")
    public ResponseEntity getIt(@RequestParam(value = "first_name", required = false) String first_name,
                                @RequestParam(value = "last_name", required = false) String last_name,
                                @RequestParam(value = "g_mail", required = false) String g_mail) {
        log.info("n , s , g :{} {} {}", first_name,last_name,g_mail);
return ResponseEntity.ok(consumerService.search(first_name,last_name,g_mail));
    }


}
