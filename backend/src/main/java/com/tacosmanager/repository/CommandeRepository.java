package com.tacosmanager.repository;

import com.tacosmanager.entity.Commande;
import com.tacosmanager.entity.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    Optional<Commande> findById(Long id);

    List<Commande> findAll();

    Commande save(Commande commande);

    void deleteById(Long id);

    boolean existsById(Long id);

//    List<Commande> findByClientId(Long clientId);

    List<Commande> findByEtat(Etat etat);

    @Query("SELECT c FROM Commande c WHERE c.dateCreation BETWEEN :startDate AND :endDate")
    List<Commande> findByDateCreationBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

//    @Query("SELECT c FROM Commande c WHERE c.client.id = :clientId AND c.etat = :etat")
//    List<Commande> findByClientIdAndEtat(@Param("clientId") Long clientId, @Param("etat") Etat etat);

    @Query("SELECT c FROM Commande c WHERE c.prixGlobal >= :minPrix")
    List<Commande> findByPrixGlobalGreaterThanEqual(@Param("minPrix") Double minPrix);

//    @Query("SELECT c FROM Commande c WHERE :produitId IN (SELECT p.id FROM c.produits p)")
//    List<Commande> findByProduitId(@Param("produitId") Long produitId);

    @Query("SELECT c FROM Commande c WHERE c.dateCreation > :date AND c.etat = :etat")
    List<Commande> findByDateCreationAfterAndEtat(@Param("date") LocalDateTime date, @Param("etat") Etat etat);
}
