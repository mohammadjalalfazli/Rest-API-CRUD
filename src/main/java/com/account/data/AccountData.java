package com.account.data;

import com.account.model.AccountModel;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AccountData {

    // Data Store for Account Entity
    public List<AccountModel> accounts  = new ArrayList<>();

    // Auto Increment Id
    public long autoIncrementNumber = 1 ;
}
