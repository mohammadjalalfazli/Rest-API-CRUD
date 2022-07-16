package com.account.controller;

import com.account.model.AccountModel;
import com.account.model.ResponseModel;
import com.account.model.ValidationModel;
import com.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    public AccountService accountService ;
    public AccountController(AccountService accountService){
         this.accountService = accountService;
     }

    // This function is used for Create Or Edit the one Account
    @PostMapping("/save")
    public ResponseModel createOrEditAccount(@RequestBody AccountModel request) {
         ResponseModel responseModel = new ResponseModel();
            try {
                if(request.getId() == 0){
                    ValidationModel validate = accountService.isValid(request);
                    if(!validate.getIsValid()){
                        throw new RuntimeException(validate.getMessage());
                    }
                }
                AccountModel Data = accountService.CreateOrEdit(request);
                responseModel.setIsSuccessful(true);
                responseModel.setStatus (HttpStatus.CREATED);
                responseModel.setMessage("Account Successfully Created!");
                responseModel.setData(Data);

             }
            catch (Exception e) {
                responseModel = new ResponseModel();
                responseModel.setIsSuccessful(false);
                responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                responseModel.setMessage(e.toString());
            }
        return responseModel;
    }

    // This function is used for create multiple Account
    @PostMapping("/saveAll")
    public ResponseModel createMultipleAccount(@RequestBody List<AccountModel> request) {
        ResponseModel responseModel = new ResponseModel();
        try {
            for (AccountModel accountModelItem: request) {
                ValidationModel validate = accountService.isValid(accountModelItem);
                if(!validate.getIsValid()){
                    throw new RuntimeException(validate.getMessage());
                }
            }
            List<AccountModel> accountModelList = new ArrayList<>();
            for (AccountModel accountModel: request ) {
                accountModelList.add(accountService.CreateOrEdit(accountModel));
            }
            responseModel.setIsSuccessful(true);
            responseModel.setStatus (HttpStatus.CREATED);
            responseModel.setMessage("All Accounts Successfully Created!");
            responseModel.setData(accountModelList);

        }
        catch (Exception e) {
            responseModel = new ResponseModel();
            responseModel.setIsSuccessful(false);
            responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setMessage(e.toString());
        }
        return responseModel;
    }

    // This function is used for list all account
    @GetMapping("/list")
    public ResponseModel getAllAccount() {
        ResponseModel responseModel = new ResponseModel();
        try {
            var list = accountService.GetList();
            responseModel.setIsSuccessful(true);
            responseModel.setStatus (HttpStatus.OK);
            responseModel.setMessage("All Accounts Successfully Retrieved!");
            responseModel.setData(list);
            if(list.size() == 0){
                responseModel.setStatus(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {

            responseModel.setIsSuccessful(false);
            responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setMessage(e.toString());
        }
        return responseModel;
    }

    // This function is used for find an account by provided id
    @GetMapping("/findById/{id}")
    public ResponseModel findAccountById(@PathVariable("id") long id) {
        ResponseModel responseModel = new ResponseModel();
        try {
            AccountModel accountModel = accountService.FindById(id);
            responseModel.setData(accountModel);
            responseModel.setIsSuccessful(true);
            responseModel.setStatus (HttpStatus.OK);
            if(accountModel.getId() != null) {
                responseModel.setMessage("Account Successfully Founded!");
            }
            else{
                throw new EntityNotFoundException("Account With Provided Id Is Not Exist!");
            }
        } catch (Exception e) {
            responseModel.setIsSuccessful(false);
            responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setMessage(e.toString());
        }
        return responseModel;
    }

    // This function is used for delete an account by provided Id
    @DeleteMapping("/deleteById/{id}")
    public ResponseModel deleteAccountById(@PathVariable("id") long id) {
        ResponseModel responseModel = new ResponseModel();
        try {
            responseModel.setIsSuccessful(true);
            responseModel.setStatus (HttpStatus.OK);
            responseModel.setMessage("Account Successfully Deleted!");
            responseModel.setData(accountService.Delete(id));

        } catch (Exception e) {
            responseModel.setIsSuccessful(false);
            responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setMessage(e.toString());
        }
        return responseModel;
    }

    // This function is used for search account by provided criteria (FirstName, LastName, Email)
    @GetMapping("/search")
    public ResponseModel searchAccountByCriteria(@RequestBody AccountModel request) {
        ResponseModel responseModel = new ResponseModel();
        try {
            var list = accountService.Search(request);
            responseModel.setIsSuccessful(true);
            responseModel.setStatus (HttpStatus.OK);
            responseModel.setMessage("Accounts Successfully Founded!");
            responseModel.setData(list);
            if(list.size() == 0){
                responseModel.setStatus(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {

            responseModel.setIsSuccessful(false);
            responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setMessage(e.toString());
        }
        return responseModel;
    }
}
