package com.tacosmanager.controller;

import com.tacosmanager.dto.FamilleProduitDTO;
import com.tacosmanager.entity.FamilleProduit;
import com.tacosmanager.entity.Fastfood;
import com.tacosmanager.service.FamilleProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/famille-produits")
public class FamilleProduitController {

    @Autowired
    private FamilleProduitService familleProduitService;

    @PostMapping
    public ResponseEntity<FamilleProduit> createFamilleProduit(@RequestBody FamilleProduitDTO dto) {
        FamilleProduit familleProduit = familleProduitService.createFamilleProduit(dto);
        return ResponseEntity.ok(familleProduit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilleProduit> getFamilleProduitById(@PathVariable Long id) {
        Optional<FamilleProduit> familleProduit = familleProduitService.getFamilleProduitById(id);
        return familleProduit.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FamilleProduit>> getAllFamilleProduits() {
        List<FamilleProduit> familleProduits = familleProduitService.getAllFamilleProduits();
        return ResponseEntity.ok(familleProduits);
    }
    //..................................................................
//    @GetMapping("/fastfood/{fastfoodId}")
//    public ResponseEntity<List<FamilleProduit>> findFamilleProduitByFastFood(@PathVariable Long fastfoodId) {
//        List<FamilleProduit> familles = familleProduitService.findFamilleProduitByFastfood(fastfoodId);
//        return ResponseEntity.ok(familles);
//    }
    //..................................................................
    @PutMapping("/{id}")
    public ResponseEntity<FamilleProduit> updateFamilleProduit(@PathVariable Long id, @RequestBody FamilleProduitDTO dto) {
        FamilleProduit familleProduit = familleProduitService.updateFamilleProduit(id, dto);
        return ResponseEntity.ok(familleProduit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamilleProduit(@PathVariable Long id) {
        familleProduitService.deleteFamilleProduit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/intitule/{intitule}")
    public ResponseEntity<FamilleProduit> findByIntitule(@PathVariable String intitule) {
        Optional<FamilleProduit> familleProduit = familleProduitService.findByIntitule(intitule);
        return familleProduit.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/intitule-contains/{intitule}")
    public ResponseEntity<List<FamilleProduit>> searchByIntitule(@PathVariable String intitule) {
        List<FamilleProduit> familleProduits = familleProduitService.searchByIntitule(intitule);
        return ResponseEntity.ok(familleProduits);
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<List<FamilleProduit>> searchByKeyword(@PathVariable String keyword) {
        List<FamilleProduit> familleProduits = familleProduitService.searchByKeyword(keyword);
        return ResponseEntity.ok(familleProduits);
    }
}