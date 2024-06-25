package com.bank.card.service;

import com.bank.card.common.exception.BankBalanceInsufficientException;
import com.bank.card.common.exception.ResourceNotFoundException;
import com.bank.card.dto.CardTransactionDto;
import com.bank.card.dto.CardTransactionSummaryDto;
import com.bank.card.entity.BankCardEntity;
import com.bank.card.entity.CardTransactionSummaryEntity;
import com.bank.card.repository.BankCardRepositoryI;
import com.bank.card.repository.CardTransactionSummaryRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardTransactionService {

    @Autowired
    CardTransactionSummaryRepositoryI cardTransactionSummaryRepositoryI;

    @Autowired
    BankCardRepositoryI bankCardRepositoryI;

    public List<CardTransactionSummaryDto> performTransaction(CardTransactionDto cardTransactionDto) {

        List<CardTransactionSummaryDto> cardTransactionSummaryDtos = new ArrayList<>();
        //check whether card name is valid of not
        BankCardEntity bankCardEntity = bankCardRepositoryI.findByCardName(cardTransactionDto.getCardName());
        if(Optional.ofNullable(bankCardEntity).isEmpty()){
            throw new ResourceNotFoundException("Record Not found with Card Name: "+ cardTransactionDto.getCardName());
        }

        CardTransactionSummaryEntity cardTransactionSummaryEntity = cardTransactionSummaryRepositoryI
                .findByBankCardCardName(cardTransactionDto.getCardName());
        if(Optional.ofNullable(cardTransactionSummaryEntity).isPresent()){

            //Transaction Summary already present
            if(!validateBankBalanceAmount(cardTransactionSummaryEntity.getAmountBalance(), cardTransactionDto.getAmount())) {
                throw new BankBalanceInsufficientException("BankBalanceInsufficientException with balance amount- "+ cardTransactionSummaryEntity.getAmountBalance() + " and transaction amount- "+ cardTransactionDto.getAmount());
            }
            cardTransactionSummaryEntity.setAmountBalance(cardTransactionSummaryEntity.getAmountBalance() - cardTransactionDto.getAmount());
            Double existingRewardPoints = cardTransactionSummaryEntity.getRewardPoints();
            cardTransactionSummaryEntity.setRewardPoints(existingRewardPoints + calculateRewardPoints(cardTransactionDto.getAmount(), bankCardEntity.getRewardPointsPerCentDolor()));
            cardTransactionSummaryRepositoryI.save(cardTransactionSummaryEntity);

        } else {
            //Construct new Transaction Summary and perform the transaction
            CardTransactionSummaryEntity cardTransactionSummaryEntityObj = new CardTransactionSummaryEntity();
            cardTransactionSummaryEntityObj.setBankCard(bankCardEntity);
            if(!validateBankBalanceAmount(bankCardEntity.getLimitAmount(), cardTransactionDto.getAmount())) {
                throw new BankBalanceInsufficientException("BankBalanceInsufficientException with balance amount- "+ bankCardEntity.getLimitAmount() + " and transaction amount- "+ cardTransactionDto.getAmount());
            }
            cardTransactionSummaryEntityObj.setAmountBalance(bankCardEntity.getLimitAmount() - cardTransactionDto.getAmount());
            cardTransactionSummaryEntityObj.setRewardPoints(calculateRewardPoints(cardTransactionDto.getAmount(), bankCardEntity.getRewardPointsPerCentDolor()));
            //persist card transaction
            cardTransactionSummaryRepositoryI.save(cardTransactionSummaryEntityObj);
        }

        //return the all card details
        List<CardTransactionSummaryEntity> cardTransactionSummaryEntities = cardTransactionSummaryRepositoryI.findAll();
        cardTransactionSummaryDtos = cardTransactionSummaryEntities.stream()
                .map(entity -> {
                    CardTransactionSummaryDto dto = new CardTransactionSummaryDto();
                    if (entity.getBankCard() != null) {
                        dto.setCardName(entity.getBankCard().getCardName());
                    }
                    dto.setAmountBalance(entity.getAmountBalance());
                    dto.setRewardPoints(entity.getRewardPoints());
                    return dto;
                })
                .collect(Collectors.toList());
        return cardTransactionSummaryDtos;
    }

    private Boolean validateBankBalanceAmount(Double existingBalance, Double transactionAmount){
        if (existingBalance == null || transactionAmount == null) {
            return false;
        }
        return existingBalance >= transactionAmount;
    }

    public static Double calculateRewardPoints(Double purchaseAmount, Double rewardPointsPerCentDollar) {
        // Calculate the number of complete 100 dollar units
        int completeCentDollarUnits = (int) (purchaseAmount / 100);
        // Calculate the reward points
        return completeCentDollarUnits * rewardPointsPerCentDollar;
    }
}
