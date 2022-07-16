package com.account;

import com.account.model.AccountModel;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class testAccountData {
    public List<AccountModel> accounts  = new ArrayList<>();

    void makeList(){
        AccountModel accountModel = new AccountModel();
        accountModel.setId(Long.valueOf(0));
        accountModel.setFirstName("Ahmad");
        accountModel.setLastName("Ahmadi");
        accountModel.setEmail("ahmad@gmail.com");
        accountModel.setDateOfBirth(Date.valueOf("2020-01-01"));
        accounts.add(accountModel);
        accountModel = new AccountModel();

        accountModel.setId(Long.valueOf(0));
        accountModel.setFirstName("Mohammad");
        accountModel.setLastName("Ahmadi");
        accountModel.setEmail("Mohammad@gmail.com");
        accountModel.setDateOfBirth(Date.valueOf("2021-01-01"));
        accounts.add(accountModel);
        accountModel = new AccountModel();

        accountModel.setId(Long.valueOf(0));
        accountModel.setFirstName("John");
        accountModel.setLastName("Ahmadi");
        accountModel.setEmail("john@gmail.com");
        accountModel.setDateOfBirth(Date.valueOf("2015-01-01"));
        accounts.add(accountModel);
        accountModel = new AccountModel();

        accountModel.setId(Long.valueOf(0));
        accountModel.setFirstName("Yama");
        accountModel.setLastName("Ahmadi");
        accountModel.setEmail("yama@gmail.com");
        accountModel.setDateOfBirth(Date.valueOf("2018-01-01"));
        accounts.add(accountModel);
    }
}
