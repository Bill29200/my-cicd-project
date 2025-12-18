package com.tacosmanager.repository;

import com.tacosmanager.entity.FamilleProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilleProduitRepository extends JpaRepository<FamilleProduit, Long> {

    Optional<FamilleProduit> findByIntitule(String intitule);

    List<FamilleProduit> findByIntituleContainingIgnoreCase(String intitule);

    @Query("SELECT fp FROM FamilleProduit fp WHERE fp.intitule LIKE %:keyword%")
    List<FamilleProduit> searchByKeyword(@Param("keyword") String keyword);
}