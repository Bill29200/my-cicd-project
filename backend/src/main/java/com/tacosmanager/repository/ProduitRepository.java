package com.tacosmanager.repository;

import com.tacosmanager.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    Optional<Produit> findById(Long id);

    List<Produit> findAll();

    Produit save(Produit produit);

    void deleteById(Long id);

    boolean existsById(Long id);

//    List<Produit> findByNomProdContainingIgnoreCase(String nomProd);

    List<Produit> findByDisponible(Boolean disponible);

    @Query("SELECT p FROM Produit p WHERE p.fastfood.fastfoodId = :fastfoodId")
    List<Produit> findByFastfoodId(@Param("fastfoodId") Long fastfoodId);

    @Query("SELECT p FROM Produit p WHERE p.familleProduit.idFamilleProduit = :familleProduitId")
    List<Produit> findByFamilleProduitId(@Param("familleProduitId") Long familleProduitId);

    @Query("SELECT p FROM Produit p WHERE p.nomProduit LIKE %:keyword% OR p.prix = :prix")
    List<Produit> searchByKeywordOrPrix(@Param("keyword") String keyword, @Param("prix") Double prix);

    List<Produit> findByPrixBetween(Double minPrix, Double maxPrix);

    @Query("SELECT p FROM Produit p WHERE p.fastfood.fastfoodId = :fastFoodId AND p.disponible = :disponible")
    List<Produit> findByFastfoodIdAndDisponible(@Param("fastFoodId") Long fastFoodId, @Param("disponible") Boolean disponible);
}
