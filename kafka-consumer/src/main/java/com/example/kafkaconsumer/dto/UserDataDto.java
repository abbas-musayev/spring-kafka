package com.example.kafkaconsumer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDataDto {
    private String username;
    private String email;
    private String password;
}
