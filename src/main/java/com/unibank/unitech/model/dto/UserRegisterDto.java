package com.unibank.unitech.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    private String email;
    @NotBlank
    private String pin;
    @NotBlank
    private String password;
}
