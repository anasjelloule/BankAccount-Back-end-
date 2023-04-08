package ma.voltify.bankweb.entities;

import java.util.Date;

import enums.OperationsType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class AccountOperations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date Date;
    private double amount;
    private OperationsType type;
    @ManyToOne
    private BankAccount bankAccount;
}
