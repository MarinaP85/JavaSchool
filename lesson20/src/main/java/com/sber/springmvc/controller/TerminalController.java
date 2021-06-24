package com.sber.springmvc.controller;

import com.sber.springmvc.exception.CardException;
import com.sber.springmvc.exception.ServiceException;
import com.sber.springmvc.model.User;
import com.sber.springmvc.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class TerminalController {
    @Autowired
    CardService cardService;

    @GetMapping(value = "/account/{account}")
    public int getMoney(ModelMap model, @PathVariable(name = "account") String account,
                        @RequestBody User user) throws ServiceException {
        int money = cardService.getMoney(account);
        model.addAttribute("user", user.getName());
        model.addAttribute("money", money);
        model.addAttribute("status", "OK");
        return money;
    }

    @PutMapping(value = "/account")
    public String setMoney(ModelMap model, @RequestParam String account, @RequestParam int sum,
                           @RequestParam String operation, @RequestBody User user)
            throws ServiceException {
        String status;
        if (operation.equals("withdraw")) {
            try {
                cardService.withdraw(account, sum);
                status = "withdraw";
            } catch (CardException e) {
                status = e.getMessage();
            }
        } else {
            cardService.deposit(account, sum);
            status = "deposit";
        }
        int money = cardService.getMoney(account);
        model.addAttribute("user", user.getName());
        model.addAttribute("money", money);
        model.addAttribute("status", status);
        return status;
    }

}
