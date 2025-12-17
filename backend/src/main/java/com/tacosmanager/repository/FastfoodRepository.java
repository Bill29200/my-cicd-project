package com.tacosmanager.repository;

import com.tacosmanager.entity.FamilleProduit;
import com.tacosmanager.entity.Fastfood;
import com.tacosmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FastfoodRepository extends JpaRepository<Fastfood, Long> {

    Optional<Fastfood> findById(Long id);

    List<Fastfood> findAll();

    Fastfood save(Fastfood fastfood);

    void deleteById(Long id);

    boolean existsById(Long id);

    Optional<Fastfood> findByIntitule(String intitule);

    List<Fastfood> findByAdresseContainingIgnoreCase(String adresse);

    @Query("SELECT f FROM Fastfood f WHERE f.gerant.nom = :nomGerant")
    Optional<Fastfood> findFastfoodByNomGerant(@Param("nomGerant") String nomGerant);

//    Optional<Fastfood> findByMail(String mail);

//    @Query("SELECT f FROM Fastfood f WHERE f.gerant.id = :gerantId")
//    Optional<Fastfood> findByGerantId(@Param("gerantId") Long gerantId);

//    @Query("SELECT f FROM Fastfood f WHERE f.serveur.id = :serveurId")
//    Optional<Fastfood> findByServeurId(@Param("serveurId") Long serveurId);

    @Query("SELECT f FROM Fastfood f WHERE f.intitule LIKE %:keyword% OR f.adresse LIKE %:keyword%")
    List<Fastfood> searchByKeyword(@Param("keyword") String keyword);

//    @Query("SELECT f FROM Fastfood f WHERE f.clients IS NOT EMPTY")
//    List<Fastfood> findWithClients();

//    @Query("SELECT f FROM Fastfood f WHERE SIZE(f.produits) > :minProduits")
//    List<Fastfood> findByMinProduits(@Param("minProduits") int minProduits);
}