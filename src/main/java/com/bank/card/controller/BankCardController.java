package com.bank.card.controller;

import com.bank.card.dto.BankCardDetailsDto;
import com.bank.card.dto.CardTransactionDto;
import com.bank.card.dto.CardTransactionSummaryDto;
import com.bank.card.service.BankCardService;
import com.bank.card.service.CardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/bank/card")
public class BankCardController {

    @Autowired
    BankCardService bankCardService;

    @Autowired
    CardTransactionService cardTransactionService;

    @GetMapping("/{cardName}")
    public ResponseEntity<Object> getBankCardDetails(@PathVariable(value = "cardName") String cardName){
        Map<String, Object> body = new HashMap<>();
        if(Optional.ofNullable(cardName).isPresent()){
            BankCardDetailsDto bankCardDetailsDto = bankCardService.getBankCardDetails(cardName);
            body.put("responseData", bankCardDetailsDto);
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
        body.put("responseData", "card name is not present");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Object> performTransaction(@RequestBody CardTransactionDto cardTransactionDto){
        Map<String, Object> body = new HashMap<>();
        if(Optional.ofNullable(cardTransactionDto).isPresent()
                && Optional.ofNullable(cardTransactionDto.getCardName()).isPresent()
                && Optional.ofNullable(cardTransactionDto.getAmount()).isPresent()){
            List<CardTransactionSummaryDto> bankCardDetailsDto = cardTransactionService.performTransaction(cardTransactionDto);
            body.put("responseData", bankCardDetailsDto);
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
        body.put("responseData", "card name is not present");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
