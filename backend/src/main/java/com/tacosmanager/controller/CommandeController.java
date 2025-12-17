package com.tacosmanager.controller;

import com.tacosmanager.entity.Commande;
import com.tacosmanager.entity.Etat;
import com.tacosmanager.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping("/{id}")
    public ResponseEntity<Commande> findById(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.findById(id);
        return commande.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Commande>> findAll() {
        List<Commande> commandes = commandeService.findAll();
        return ResponseEntity.ok(commandes);
    }

    @PostMapping
    public ResponseEntity<Commande> save(@RequestBody Commande commande) {
        Commande savedCommande = commandeService.save(commande);
        return ResponseEntity.ok(savedCommande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commandeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        boolean exists = commandeService.existsById(id);
        return ResponseEntity.ok(exists);
    }

//    @GetMapping("/client/{clientId}")
//    public ResponseEntity<List<Commande>> findByClientId(@PathVariable Long clientId) {
//        List<Commande> commandes = commandeService.findByClientId(clientId);
//        return ResponseEntity.ok(commandes);
//    }

    @GetMapping("/etat/{etat}")
    public ResponseEntity<List<Commande>> findByEtat(@PathVariable Etat etat) {
        List<Commande> commandes = commandeService.findByEtat(etat);
        return ResponseEntity.ok(commandes);
    }

    @GetMapping("/date-between")
    public ResponseEntity<List<Commande>> findByDateCreationBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Commande> commandes = commandeService.findByDateCreationBetween(startDate, endDate);
        return ResponseEntity.ok(commandes);
    }

//    @GetMapping("/client-etat/{clientId}/{etat}")
//    public ResponseEntity<List<Commande>> findByClientIdAndEtat(
//            @PathVariable Long clientId,
//            @PathVariable Etat etat) {
//        List<Commande> commandes = commandeService.findByClientIdAndEtat(clientId, etat);
//        return ResponseEntity.ok(commandes);
//    }

    @GetMapping("/prix-min/{minPrix}")
    public ResponseEntity<List<Commande>> findByPrixGlobalGreaterThanEqual(
            @PathVariable Double minPrix) {
        List<Commande> commandes = commandeService.findByPrixGlobalGreaterThanEqual(minPrix);
        return ResponseEntity.ok(commandes);
    }

//    @GetMapping("/produit/{produitId}")
//    public ResponseEntity<List<Commande>> findByProduitId(@PathVariable Long produitId) {
//        List<Commande> commandes = commandeService.findByProduitId(produitId);
//        return ResponseEntity.ok(commandes);
//    }

    @GetMapping("/date-after-etat")
    public ResponseEntity<List<Commande>> findByDateCreationAfterAndEtat(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Etat etat) {
        List<Commande> commandes = commandeService.findByDateCreationAfterAndEtat(date, etat);
        return ResponseEntity.ok(commandes);
    }
}