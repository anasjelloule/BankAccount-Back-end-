package ma.voltify.bankweb.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CUR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CurrentAccount extends BankAccount {
    private double overDraft;
}
