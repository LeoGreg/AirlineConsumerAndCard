package com.example.general.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VerifyDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String code;
}