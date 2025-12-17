package com.tacosmanager.controller;

import com.tacosmanager.dto.ProduitDTO;
import com.tacosmanager.entity.Produit;
import com.tacosmanager.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody ProduitDTO dto) {
        Produit produit = produitService.createProduit(dto);
        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        return produit.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody ProduitDTO dto) {
        Produit updatedProduit = produitService.updateProduit(id, dto);
        return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/search/nom")
//    public ResponseEntity<List<Produit>> searchByNomProd(@RequestParam String nomProd) {
//        List<Produit> produits = produitService.searchByNomProd(nomProd);
//        return new ResponseEntity<>(produits, HttpStatus.OK);
//    }

    @GetMapping("/filter/disponible")
    public ResponseEntity<List<Produit>> filterByDisponible(@RequestParam Boolean disponible) {
        List<Produit> produits = produitService.filterByDisponible(disponible);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/fastfood/{fastfoodId}")
    public ResponseEntity<List<Produit>> findByFastfoodId(@PathVariable Long fastfoodId) {
        List<Produit> produits = produitService.findByFastfoodId(fastfoodId);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/famille/{familleProduitId}")
    public ResponseEntity<List<Produit>> findByFamilleProduitId(@PathVariable Long familleProduitId) {
        List<Produit> produits = produitService.findByFamilleProduitId(familleProduitId);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/search/keyword-or-prix")
    public ResponseEntity<List<Produit>> searchByKeywordOrPrix(@RequestParam String keyword, @RequestParam Double prix) {
        List<Produit> produits = produitService.searchByKeywordOrPrix(keyword, prix);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/prix-range")
    public ResponseEntity<List<Produit>> findByPrixRange(@RequestParam Double minPrix, @RequestParam Double maxPrix) {
        List<Produit> produits = produitService.findByPrixRange(minPrix, maxPrix);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/fastfood-disponible")
    public ResponseEntity<List<Produit>> findByFastfoodIdAndDisponible(@RequestParam Long fastFoodId, @RequestParam Boolean disponible) {
        List<Produit> produits = produitService.findByFastfoodIdAndDisponible(fastFoodId, disponible);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }
}