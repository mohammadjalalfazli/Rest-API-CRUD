package com.account.service;

import com.account.data.AccountData;
import com.account.model.AccountModel;
import com.account.model.ValidationModel;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

@Configuration
public class AccountService {

    public AccountData accountData;
    public AccountService(AccountData accountData){
        this.accountData = accountData;
    }
    private String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";


    // Create if the given model dos'nt have Id and edit when provided Id
    public AccountModel CreateOrEdit(AccountModel request){
        if(request.getId() == 0){
            request.setId(accountData.autoIncrementNumber);
            accountData.accounts.add(request);
            accountData.autoIncrementNumber++;
        }
        else{

            for (AccountModel accountModel: accountData.accounts) {
                if(accountModel.getId() == request.getId()){
                    accountModel.setFirstName(request.getFirstName());
                    accountModel.setLastName(request.getLastName());
                    accountModel.setDateOfBirth(request.getDateOfBirth());
                    accountModel.setEmail(request.getEmail());
                }
            }
        }
        return  request;
    }

    // Return all Item
    public List<AccountModel> GetList(){
        return accountData.accounts;
    }

    // Delete the Item According to the provided Id
    public AccountModel Delete(long id){

        AccountModel accountModelRes = new AccountModel();
        for (AccountModel accountModel: accountData.accounts
             ) {
            if(accountModel.getId() == id){
                accountModelRes = accountModel;
                accountData.accounts.remove(accountModel);
                break;
            }
        }
        return accountModelRes;
    }

    // Find Item According to the Provided Id
    public AccountModel FindById(long id){
        AccountModel accountModelRes = new AccountModel();
        for (AccountModel accountModel: accountData.accounts
        ) {
            if(accountModel.getId() == id){
                accountModelRes = accountModel;
                break;
            }
        }
        return accountModelRes;
    }

    // Search All Item by the Search Chreteria Rether than Id
    public List<AccountModel> Search(AccountModel request){

        List<AccountModel> res = new ArrayList<>();

        for (var x :accountData.accounts )
        {
            res.add(x);
        }


        for (AccountModel accountModel: accountData.accounts)
        {
            if(request.getFirstName() != "" && !accountModel.getFirstName() .equals(request.getFirstName()) ){
                res.remove(accountModel);
                continue;
            }
            if(request.getLastName() != "" && !accountModel.getLastName().equals( request.getLastName())){
                res.remove(accountModel);
                continue;
            }
            if(request.getEmail() != "" && !accountModel.getEmail().equals(request.getEmail()) ){
                res.remove(accountModel);
                continue;
            }
        }
        return res;
    }

    // Check Validation of Request
    public ValidationModel isValid(AccountModel request){
        ValidationModel response= new ValidationModel(){};
        response.setIsValid(true);

        String Message ="";
        if(request.getFirstName() == "" || request.getFirstName() == null){
            Message += "First Name is Invalid";
            response.setIsValid(false);
        }
        if(request.getLastName() =="" || request.getLastName() == null){
            Message += "Last Name is Invalid";
            response.setIsValid(false);
        }
        if(CheckEmailDuplicate(request.getEmail())){
            Message += "The Email Address Provided Allready Exist";
            response.setIsValid(false);
        }
        if(request.getDateOfBirth().after( Date.valueOf(LocalDate.now()))){
            Message += "Date Of Birth is Invalid";
            response.setIsValid(false);
        }

        response.setMessage(Message);
        return  response;
    }

    // Check Email Duplication
    public Boolean CheckEmailDuplicate(String email){
        if(!isValidEmail(email)){
            return false;
        }
        for (AccountModel accountModel: accountData.accounts) {
            if(accountModel.getEmail() == email){
                return true;
            }
        }
        return false;
    }

    // Check Valid Email
    public Boolean isValidEmail(String email){
        return Pattern.compile(regexPattern).matcher(email).matches();
    }
}
