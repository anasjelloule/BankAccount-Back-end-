package entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class SavingAccount extends BankAccount {
    private double interestrate;
}
