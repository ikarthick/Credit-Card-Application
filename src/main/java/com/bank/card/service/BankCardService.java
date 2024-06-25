package com.bank.card.service;

import com.bank.card.common.exception.ResourceNotFoundException;
import com.bank.card.dto.BankCardDetailsDto;
import com.bank.card.entity.BankCardEntity;
import com.bank.card.repository.BankCardRepositoryI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankCardService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BankCardRepositoryI bankCardRepositoryI;

    public BankCardDetailsDto getBankCardDetails(String cardName){
        BankCardEntity bankCardEntity = bankCardRepositoryI.findByCardName(cardName);
        if(Optional.ofNullable(bankCardEntity).isPresent()){
            return modelMapper.map(bankCardEntity, BankCardDetailsDto.class);
        } else {
            throw new ResourceNotFoundException("Record Not found with Card Name: "+ cardName);
        }
    }

}
