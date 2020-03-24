package com.example.transferapp;

import com.example.transferapp.entity.BankAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TransferAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferAppApplication.class, args);
        List<BankAccount> bankAccounts = new ArrayList<>();

        if (Optional.ofNullable(bankAccounts).isPresent()) {
            List<BankAccount> account = Optional.ofNullable(bankAccounts).get();
        }

        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new BankAccount("Tom", 1000));
            session.save(new BankAccount("Jerry", 2000));
            session.save(new BankAccount("Donald", 3000));
            session.getTransaction().commit();
        }catch (Exception e){

        }

        sessionFactory.close();
    }

}
