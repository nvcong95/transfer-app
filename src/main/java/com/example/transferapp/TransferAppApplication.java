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
    }

}
