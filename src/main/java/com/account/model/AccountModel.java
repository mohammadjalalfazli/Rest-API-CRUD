package com.account.model;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;


@Getter
@Setter
public class AccountModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;

}
