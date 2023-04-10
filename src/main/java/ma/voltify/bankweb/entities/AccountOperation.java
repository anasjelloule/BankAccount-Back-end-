package ma.voltify.bankweb.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import enums.OperationsType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date Date;
    private double amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private OperationsType type;
    @ManyToOne
    @ToString.Exclude
    private BankAccount bankAccount;
}
