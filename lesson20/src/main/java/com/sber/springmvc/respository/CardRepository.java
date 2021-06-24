package com.sber.springmvc.respository;

import com.sber.springmvc.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CardRepository extends JpaRepository<Card, Integer> {
    @Query("select c.money from Card c where c.account = :account")
    int readMoney(@Param("account") String account);

    @Modifying
    @Query("update Card c set c.money = :sum where c.account = :account")
    void setMoney(@Param("account") String account, @Param("sum") int sum);
}
