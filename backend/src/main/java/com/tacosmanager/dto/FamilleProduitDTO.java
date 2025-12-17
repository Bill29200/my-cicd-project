package com.tacosmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FamilleProduitDTO {
    private Long idFamilleProduit;
    private String intitule;
}