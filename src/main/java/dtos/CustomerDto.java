package dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
}
