package com.egs.bank.repository;

import com.egs.bank.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findAllByCardNumber(@Param("cardNumber") String cardNumber);
    Card findAllByCardNumberAndPinType(@Param("cardNumber") String cardNumber, @Param("pinType") String pinType);
}