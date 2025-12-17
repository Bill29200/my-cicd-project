package com.tacosmanager.controller;

import com.tacosmanager.dto.FastfoodDTO;
import com.tacosmanager.entity.Fastfood;
import com.tacosmanager.service.FastfoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fastfoods")
public class FastfoodController {

    @Autowired
    private FastfoodService fastfoodService;
    //..........................................................................................CREATE..................
    @PostMapping
    public ResponseEntity<Fastfood> createFastfood(@RequestBody FastfoodDTO dto) {
        Fastfood fastfood = fastfoodService.createFastfood(dto);
        return ResponseEntity.ok(fastfood);
    }
    //..........................................................................................READ..................
    @GetMapping("/{id}")
    public ResponseEntity<Fastfood> getFastfoodById(@PathVariable Long id) {
        Optional<Fastfood> fastfood = fastfoodService.getFastfoodById(id);
        return fastfood.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Fastfood>> getAllFastfoods() {
        List<Fastfood> fastfoods = fastfoodService.getAllFastfoods();
        return ResponseEntity.ok(fastfoods);
    }
//    @GetMapping("/search/keyword/{keyword}")
//    public ResponseEntity<List<Fastfood>> searchByKeyword(@PathVariable String keyword) {
//        List<Fastfood> fastfoods = fastfoodService.searchByKeyword(keyword);
//        return ResponseEntity.ok(fastfoods);
//    }
//@GetMapping("/search/intitule/{intitule}")
//public ResponseEntity<Fastfood> findByIntitule(@PathVariable String intitule) {
//    Optional<Fastfood> fastfood = fastfoodService.findByIntitule(intitule);
//    return fastfood.map(ResponseEntity::ok)
//            .orElseGet(() -> ResponseEntity.notFound().build());
//}
//
//    @GetMapping("/search/adresse/{adresse}")
//    public ResponseEntity<List<Fastfood>> searchByAdresse(@PathVariable String adresse) {
//        List<Fastfood> fastfoods = fastfoodService.searchByAdresse(adresse);
//        return ResponseEntity.ok(fastfoods);
//    }

//    @GetMapping("/search/mail/{mail}")
//    public ResponseEntity<Fastfood> findByMail(@PathVariable String mail) {
//        Optional<Fastfood> fastfood = fastfoodService.findByMail(mail);
//        return fastfood.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    @GetMapping("/search/gerant/{gerantId}")
//    public ResponseEntity<Fastfood> findByGerantId(@PathVariable Long gerantId) {
//        Optional<Fastfood> fastfood = fastfoodService.findByGerantId(gerantId);
//        return fastfood.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/search/serveur/{serveurId}")
//    public ResponseEntity<Fastfood> findByServeurId(@PathVariable Long serveurId) {
//        Optional<Fastfood> fastfood = fastfoodService.findByServeurId(serveurId);
//        return fastfood.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }


//    @GetMapping("/with-clients")
//    public ResponseEntity<List<Fastfood>> findWithClients() {
//        List<Fastfood> fastfoods = fastfoodService.findWithClients();
//        return ResponseEntity.ok(fastfoods);
//    }

//    @GetMapping("/min-produits/{minProduits}")
//    public ResponseEntity<List<Fastfood>> findByMinProduits(@PathVariable int minProduits) {
//        List<Fastfood> fastfoods = fastfoodService.findByMinProduits(minProduits);
//        return ResponseEntity.ok(fastfoods);
//    }
    //..........................................................................................UPDATE..................

    @PutMapping("/{id}")
    public ResponseEntity<Fastfood> updateFastfood(@PathVariable Long id, @RequestBody FastfoodDTO dto) {
        Fastfood fastfood = fastfoodService.updateFastfood(id, dto);
        return ResponseEntity.ok(fastfood);
    }
    //..........................................................................................DELETE..................

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFastfood(@PathVariable Long id) {
        fastfoodService.deleteFastfood(id);
        return ResponseEntity.noContent().build();
    }



}