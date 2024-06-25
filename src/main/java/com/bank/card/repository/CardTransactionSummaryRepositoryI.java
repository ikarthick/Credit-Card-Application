package com.bank.card.repository;

import com.bank.card.entity.CardTransactionSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTransactionSummaryRepositoryI extends JpaRepository<CardTransactionSummaryEntity, Integer> {

    CardTransactionSummaryEntity findByBankCardCardName(String cardName);
}
