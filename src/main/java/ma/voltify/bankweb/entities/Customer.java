package ma.voltify.bankweb.entities;

import java.util.List;

import jakarta.persistence.CascadeType;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    // @JsonIgnore
    private List<BankAccount> bankAccounts;

    @PreRemove
    private void removecustomerFromBankAccounts() {
        bankAccounts.forEach(bankAccount -> bankAccount.setCustomer(null));
    }
}
