package az.company.kafkaproducer.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserDataDto {
    private String username;
    private String password;
    private String email;
}
