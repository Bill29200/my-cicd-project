package com.tacosmanager.service;

import com.tacosmanager.dto.ProduitDTO;
import com.tacosmanager.entity.FamilleProduit;
import com.tacosmanager.entity.Fastfood;
import com.tacosmanager.entity.Produit;
import com.tacosmanager.repository.ProduitRepository;
import com.tacosmanager.repository.FamilleProduitRepository;
import com.tacosmanager.repository.FastfoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private FamilleProduitRepository familleProduitRepository;

    @Autowired
    private FastfoodRepository fastfoodRepository;

    public Produit createProduit(ProduitDTO dto) {
        Produit produit = new Produit();
        mapDtoToEntity(dto, produit);
        return produitRepository.save(produit);
    }

    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit updateProduit(Long id, ProduitDTO dto) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));
        mapDtoToEntity(dto, produit);
        return produitRepository.save(produit);
    }

    public void deleteProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit non trouvé avec l'ID : " + id);
        }
        produitRepository.deleteById(id);
    }

//    public List<Produit> searchByNomProd(String nomProd) {
//        return produitRepository.findByNomProdContainingIgnoreCase(nomProd);
//    }

    public List<Produit> filterByDisponible(Boolean disponible) {
        return produitRepository.findByDisponible(disponible);
    }

    public List<Produit> findByFastfoodId(Long fastfoodId) {
        return produitRepository.findByFastfoodId(fastfoodId);
    }

    public List<Produit> findByFamilleProduitId(Long familleProduitId) {
        return produitRepository.findByFamilleProduitId(familleProduitId);
    }

    public List<Produit> searchByKeywordOrPrix(String keyword, Double prix) {
        return produitRepository.searchByKeywordOrPrix(keyword, prix);
    }

    public List<Produit> findByPrixRange(Double minPrix, Double maxPrix) {
        return produitRepository.findByPrixBetween(minPrix, maxPrix);
    }

    public List<Produit> findByFastfoodIdAndDisponible(Long fastfoodId, Boolean disponible) {
        return produitRepository.findByFastfoodIdAndDisponible(fastfoodId, disponible);
    }

    private void mapDtoToEntity(ProduitDTO dto, Produit produit) {
        produit.setNomProduit(dto.getNomProd());
        produit.setPrix(dto.getPrix());
        produit.setDisponible(dto.getDisponible());
        produit.setPhoto(dto.getPhoto());

        FamilleProduit familleProduit = familleProduitRepository.findById(dto.getFamilleProduitId())
                .orElseThrow(() -> new RuntimeException("FamilleProduit non trouvée avec l'ID : " + dto.getFamilleProduitId()));
        produit.setFamilleProduit(familleProduit);

        if (dto.getFastfoodId() != null) {
            Fastfood fastfood = fastfoodRepository.findById(dto.getFastfoodId())
                    .orElseThrow(() -> new RuntimeException("Fastfood non trouvé avec l'ID : " + dto.getFastfoodId()));
            produit.setFastfood(fastfood);
        }
    }
}