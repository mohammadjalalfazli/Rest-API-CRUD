package com.account;

import com.account.controller.AccountController;
import com.account.data.AccountData;
import com.account.model.AccountModel;
import com.account.service.AccountService;
import lombok.extern.java.Log;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)

class AccountApplicationTests {

    public AccountData accountData;
    public AccountService accountService ;
    public AccountController accountController;
    public testAccountData testAccountData;
    AccountModel accountModel = new AccountModel();

    @Autowired
    public AccountApplicationTests(
            AccountService accountService,
            AccountData accountData,
            AccountController accountController,
            testAccountData testAccountData){
        this.accountData = accountData;
        this.accountService = accountService;
        this.accountController = accountController;
        this.testAccountData =testAccountData;

        testAccountData.makeList();

        accountModel.setId(Long.valueOf(1));
        accountModel.setFirstName("Ahmad Khan");
        accountModel.setLastName("Ahmadi");
        accountModel.setEmail("ahmad@gmail.com");
        accountModel.setDateOfBirth(Date.valueOf("2000-01-01"));

    }


    @Test
    void contextLoads() {
    }

    @Test
    @Order(1)
    void testCreateMultipleAccount() {
         assertEquals(true, accountController.createMultipleAccount(testAccountData.accounts).getIsSuccessful(),"incorrect");
    }

    @Test
    @Order(2)
    void testCreateOrEditAccount() {
        assertEquals(true, accountController.createOrEditAccount(accountModel).getIsSuccessful(),"incorrect");
    }

    @Test
    @Order(3)
    void testIsValidEmail() {
        String emailAddress = "ahmadgmail.com";
        assertEquals(false,accountService.isValidEmail(emailAddress),"incorrect");
    }

    @Test
    @Order(4)
    void testCheckEmailDuplicate() {
        String emailAddress = "ahmad@gmail.com";
        assertEquals(false,accountService.CheckEmailDuplicate(emailAddress),"incorrect");
    }

    @Test
    @Order(5)
    void testValidationModel() {
        assertEquals(true,accountService.isValid(accountModel).getIsValid(),"incorrect");
    }

    @Test
    @Order(6)
    void testGetAllAccount() {
        assertEquals(true,accountController.getAllAccount().getIsSuccessful(),"incorrect");
    }

    @Test
    @Order(7)
    void testFindAccountById() {
        assertEquals(true,accountController.findAccountById(1).getIsSuccessful(),"incorrect");
    }

    @Test
    @Order(8)
    void testDeleteAccountById() {
        assertEquals(true,accountController.deleteAccountById(3).getIsSuccessful(),"incorrect");
    }

    @Test
    @Order(9)
    void testSearchAccountByCriteria() {
        assertEquals(true,accountController.searchAccountByCriteria(accountModel).getIsSuccessful(),"incorrect");
    }
}
