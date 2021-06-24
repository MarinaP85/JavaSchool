package com.sber.springmvc.service;

import com.sber.springmvc.exception.CardException;
import com.sber.springmvc.exception.ServiceException;
import com.sber.springmvc.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    @Transactional
    public int getMoney(String account) throws ServiceException {
        try {
            return cardRepository.readMoney(account);
        } catch (Exception e) {
            throw new ServiceException("Ошибка базы данных: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void withdraw(String account, int sum) throws ServiceException, CardException {
        try {
            int money = getMoney(account);
            if (money < sum) {
                throw new CardException("Недостаточно денег на счете", account);
            } else {
                cardRepository.setMoney(account, money - sum);
            }
        } catch (Exception e) {
            throw new ServiceException("Ошибка базы данных: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deposit(String account, int sum) throws ServiceException {
        try {
            int money = getMoney(account);
            cardRepository.setMoney(account, money + sum);
        } catch (Exception e) {
            throw new ServiceException("Ошибка базы данных: " + e.getMessage());
        }
    }
}
