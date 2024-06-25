package com.bank.card.repository;

import com.bank.card.entity.BankCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepositoryI extends JpaRepository<BankCardEntity, Integer> {

    BankCardEntity findByCardName(String cardName);
}
