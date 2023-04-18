package ma.voltify.bankweb.entities;

import java.util.Collection;
import java.util.Date;

import enums.AccountStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.DiscriminatorColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE", length = 4) /* Also Type discriminatorType = DiscriminatorType.STRING */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public abstract class BankAccount {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;
    @ToString.Exclude
    @ManyToOne
    private Customer customer;
    @ToString.Exclude
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.EAGER)
    // @JsonProperty(access = Access.WRITE_ONLY)
    private Collection<AccountOperation> Accountoperations;

}
