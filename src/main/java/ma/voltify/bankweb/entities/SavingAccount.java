package ma.voltify.bankweb.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SavingAccount extends BankAccount {
    private double interestrate;
}
