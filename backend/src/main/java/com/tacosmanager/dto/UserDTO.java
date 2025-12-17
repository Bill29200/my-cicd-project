package com.tacosmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tacosmanager.entity.Fastfood;
import com.tacosmanager.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    private String telephone;

   // @NotNull(message = "Le rôle est obligatoire")
    private Role role;
    @JsonProperty("fastfood")
    private Fastfood fastfood;
}