package dtos;

import enums.OperationsType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OperationDto {
    private Long id;
    private Date Date;
    private double amount;
    private String description;
    private OperationsType type;
    private BankDto BankDto;
    private String BankId;
}
