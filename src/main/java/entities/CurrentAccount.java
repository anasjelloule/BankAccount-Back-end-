package entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class CurrentAccount extends BankAccount {
    private double overDraft;
}
