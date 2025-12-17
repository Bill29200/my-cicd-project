package com.tacosmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDTO {
    private Long idProduit;
    private String nomProd;
    private Double prix;
    private Long idFamilleProduit;
    private Boolean disponible;
    private String photo;
    private Long fastfoodId;


    public Long getFamilleProduitId() {
        return idFamilleProduit;
    }
}