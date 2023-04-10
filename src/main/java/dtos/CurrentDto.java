package dtos;

import java.util.Date;

import enums.AccountStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrentDto {
    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private double overDraft;
    private CustomerDto customer;

}
