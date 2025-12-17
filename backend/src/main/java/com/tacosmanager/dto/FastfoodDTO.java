package com.tacosmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastfoodDTO {
    private Long idFastfood;
    private String intitule;
    private String logoImage;
    private String adresse;
//    private String telephone;
//    private String mail;
//    private String gerant;
//    private String motdepasse;
//    private Long gerantId;
//    private Long serveurId;
}