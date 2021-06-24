package com.sber.springmvc.service;

import com.sber.springmvc.exception.CardException;
import com.sber.springmvc.exception.ServiceException;

public interface CardService {
    //получение текущей суммы на счете

    /**
     * ...
     *
     * @param account номер счета
     * @throws ServiceException если возникла ошибка при обращении к БД
     */
    int getMoney(String account) throws ServiceException;

    //снятие денег со счета

    /**
     * ...
     *
     * @param account номер счета
     * @param sum     сумма, которую следует снять со счета
     * @throws ServiceException если возникла ошибка при обращении к БД
     * @throws CardException    если на счете не достаточно денег.
     */
    void withdraw(String account, int sum) throws ServiceException, CardException;

    //пополнение счета

    /**
     * ...
     *
     * @param account номер счета
     * @param sum     сумма, которую следует положить на счет
     * @throws ServiceException если возникла ошибка при обращении к БД
     */
    void deposit(String account, int sum) throws ServiceException;
}
