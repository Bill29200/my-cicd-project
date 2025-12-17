package com.tacosmanager.repository;

import com.tacosmanager.entity.Role;
import com.tacosmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNom(String nom);

    Optional<User> findById(Long id);

    List<User> findAll();

    User save(User user);

    void deleteById(Long id);

    boolean existsById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findByNomContainingIgnoreCase(String nom);

    List<User> findByRole(Role role);

    @Query("SELECT u FROM User u WHERE u.fastfood.fastfoodId = :fastfoodId")
    List<User> findByFastfoodId(@Param("fastfoodId") Long fastfoodId);

    @Query("SELECT u FROM User u WHERE u.nom LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<User> searchByKeyword(@Param("keyword") String keyword);

    List<User> findByTelephoneNotNull();

    @Query("SELECT u FROM User u WHERE u.role = :role AND u.fastfood.fastfoodId = :fastfoodId")
    List<User> findByRoleAndFastfoodId(@Param("role") Role role, @Param("fastfoodId") Long fastfoodId);
}
