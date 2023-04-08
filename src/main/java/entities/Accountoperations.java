package entities;

import java.util.Date;

import enums.OperationsType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Accountoperations {
    @Id
    private Long id;
    private Date Date;
    private double amount;
    private OperationsType type;
    @ManyToOne
    private BankAccount bankAccount;
}
