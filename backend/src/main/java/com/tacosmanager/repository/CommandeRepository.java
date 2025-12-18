package com.tacosmanager.repository;

import com.tacosmanager.entity.Commande;
import com.tacosmanager.entity.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByEtat(Etat etat);

    @Query("SELECT c FROM Commande c WHERE c.dateCreation BETWEEN :startDate AND :endDate")
    List<Commande> findByDateCreationBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT c FROM Commande c WHERE c.prixGlobal >= :minPrix")
    List<Commande> findByPrixGlobalGreaterThanEqual(@Param("minPrix") Double minPrix);

    @Query("SELECT c FROM Commande c WHERE c.dateCreation > :date AND c.etat = :etat")
    List<Commande> findByDateCreationAfterAndEtat(@Param("date") LocalDateTime date, @Param("etat") Etat etat);
}
