package com.example.transferapp.controller;

import com.example.transferapp.dao.BankAccountDAO;
import com.example.transferapp.entity.BankAccount;
import com.example.transferapp.exceptrion.BankTransactionException;
import com.example.transferapp.from.SendMoneyFrom;
import com.example.transferapp.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class MainController {
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ShowBankAccounts(Model model) {
        List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();
        model.addAttribute("accountInfos", list);
        return "sendMoneyPage";
    }
    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String viewSendMoneypage(Model model) {
        SendMoneyFrom form = new SendMoneyFrom(1L, 2L, 700);
        model.addAttribute("sendMoneyForm", form);
        return "sendMoneyPage";
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyFrom sendMoneyFrom) {
        System.out.println("Send Money: " + sendMoneyFrom.getAmount());
        try {
            bankAccountDAO.sendMoney(sendMoneyFrom.getFromAccountId(), sendMoneyFrom.getToAccountId(), sendMoneyFrom.getAmount());

        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/";
    }

}
