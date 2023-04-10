package dtos;

import java.util.Date;

import enums.AccountStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SavingDto {
    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private CustomerDto customer;
    private double interestrate;
}
