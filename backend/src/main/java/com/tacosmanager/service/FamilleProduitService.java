package com.tacosmanager.service;

import com.tacosmanager.dto.FamilleProduitDTO;
import com.tacosmanager.entity.FamilleProduit;
import com.tacosmanager.repository.FamilleProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilleProduitService {

    @Autowired
    private FamilleProduitRepository familleProduitRepository;
    //..........................................................................................CREATE..................
    public FamilleProduit createFamilleProduit(FamilleProduitDTO dto) {
        FamilleProduit familleProduit = new FamilleProduit();
        mapDtoToEntity(dto, familleProduit);
        return familleProduitRepository.save(familleProduit);
    }
    //..........................................................................................READ....................
    public Optional<FamilleProduit> getFamilleProduitById(Long id) {
        return familleProduitRepository.findById(id);
    }

    public List<FamilleProduit> getAllFamilleProduits() {
        return familleProduitRepository.findAll();
    }

//    public List<FamilleProduit> findFamilleProduitByFastfood(Long fastfoodId) {
//        return familleProduitRepository.findFamilleProduitByFastfood(fastfoodId);
//    }

    public Optional<FamilleProduit> findByIntitule(String intitule) {
        return familleProduitRepository.findByIntitule(intitule);
    }

    public List<FamilleProduit> searchByIntitule(String intitule) {
        return familleProduitRepository.findByIntituleContainingIgnoreCase(intitule);
    }

    public List<FamilleProduit> searchByKeyword(String keyword) {
        return familleProduitRepository.searchByKeyword(keyword);
    }
    //..........................................................................................UPDATE..................
    public FamilleProduit updateFamilleProduit(Long id, FamilleProduitDTO dto) {
        FamilleProduit familleProduit = familleProduitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FamilleProduit non trouvée avec l'ID : " + id));
        mapDtoToEntity(dto, familleProduit);
        return familleProduitRepository.save(familleProduit);
    }
    //..........................................................................................DELETE..................
    public void deleteFamilleProduit(Long id) {
        if (!familleProduitRepository.existsById(id)) {
            throw new RuntimeException("FamilleProduit non trouvée avec l'ID : " + id);
        }
        familleProduitRepository.deleteById(id);
    }
    //..........................................................................................MAPDTO..................


    private void mapDtoToEntity(FamilleProduitDTO dto, FamilleProduit familleProduit) {
        familleProduit.setIntitule(dto.getIntitule());
    }
}