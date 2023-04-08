package entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Customer {

    private String id;
    private String name;
    private String email;
}
