package dtos;

import java.util.Date;

import enums.AccountStatus;

import lombok.Data;

@Data
public class BankDto {
    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private CustomerDto customer;
}
