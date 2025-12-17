package com.tacosmanager.service;

import com.tacosmanager.entity.Commande;
import com.tacosmanager.entity.Etat;
import com.tacosmanager.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public Optional<Commande> findById(Long id) {
        return commandeRepository.findById(id);
    }

    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return commandeRepository.existsById(id);
    }

//    public List<Commande> findByClientId(Long clientId) {
//        return commandeRepository.findByClientId(clientId);
//    }

    public List<Commande> findByEtat(Etat etat) {
        return commandeRepository.findByEtat(etat);
    }

    public List<Commande> findByDateCreationBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return commandeRepository.findByDateCreationBetween(startDate, endDate);
    }

//    public List<Commande> findByClientIdAndEtat(Long clientId, Etat etat) {
//        return commandeRepository.findByClientIdAndEtat(clientId, etat);
//    }

    public List<Commande> findByPrixGlobalGreaterThanEqual(Double minPrix) {
        return commandeRepository.findByPrixGlobalGreaterThanEqual(minPrix);
    }

//    public List<Commande> findByProduitId(Long produitId) {
//        return commandeRepository.findByProduitId(produitId);
//    }

    public List<Commande> findByDateCreationAfterAndEtat(LocalDateTime date, Etat etat) {
        return commandeRepository.findByDateCreationAfterAndEtat(date, etat);
    }
}