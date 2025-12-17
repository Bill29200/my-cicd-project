package com.tacosmanager.service;

import com.tacosmanager.dto.LoginDTO;
import com.tacosmanager.dto.UserDTO;
import com.tacosmanager.entity.Fastfood;
import com.tacosmanager.entity.Role;
import com.tacosmanager.entity.User;
import com.tacosmanager.repository.FastfoodRepository;
import com.tacosmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FastfoodRepository fastfoodRepository;

    public UserDTO authenticate(LoginDTO loginDTO) {

        Optional<User> userOptional = userRepository.findByNom(loginDTO.getNom());
        if (userOptional.isPresent()) {

            User user = userOptional.get();
            // Vérifier le mot de passe (comparaison directe pour cet exemple)
            if (user.getPassword().equals(loginDTO.getPassword())) {
                UserDTO userDTO = new UserDTO();
                userDTO.setNom(user.getNom());
                userDTO.setEmail(user.getEmail());
                userDTO.setTelephone(user.getTelephone());
                userDTO.setRole(user.getRole());
                userDTO.setFastfood(userOptional.get().getFastfood());

                System.out.println("UserDTO"+userDTO);
                return userDTO;
            }
        }
        throw new RuntimeException("Nom ou mot de passe incorrect");
    }
//.......................................................................................ADD............................
    public User createUser(UserDTO dto) {
        User user = new User();
        user.setNom(dto.getNom());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setTelephone(dto.getTelephone());
        user.setRole(dto.getRole());
        user.setFastfood(dto.getFastfood());
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + id));
        user.setNom(dto.getNom());
       // user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setTelephone(dto.getTelephone());
        user.setRole(dto.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + id);
        }
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> searchByNom(String nom) {
        return userRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<User> filterByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public List<User> findByFastfoodId(Long fastfoodId) {
        return userRepository.findByFastfoodId(fastfoodId);
    }

    public List<User> searchByKeyword(String keyword) {
        return userRepository.searchByKeyword(keyword);
    }

    public List<User> findUsersWithTelephone() {
        return userRepository.findByTelephoneNotNull();
    }

    public List<User> findByRoleAndFastfoodId(Role role, Long fastfoodId) {
        return userRepository.findByRoleAndFastfoodId(role, fastfoodId);
    }
}