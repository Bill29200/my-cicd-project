package com.tacosmanager.dto;

import com.tacosmanager.entity.Etat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDTO {
    private Long id;
    private Long userId;
    private List<Long> produitIds;
    private Double prixGlobal;
    private Etat etat;
    private LocalDateTime dateCreation;


}