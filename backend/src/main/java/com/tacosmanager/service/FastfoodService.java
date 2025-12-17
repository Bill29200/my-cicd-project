package com.tacosmanager.service;

import com.tacosmanager.dto.FastfoodDTO;
import com.tacosmanager.entity.FamilleProduit;
import com.tacosmanager.entity.Fastfood;
import com.tacosmanager.entity.User;
import com.tacosmanager.repository.FastfoodRepository;
import com.tacosmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FastfoodService {

    @Autowired
    private FastfoodRepository fastfoodRepository;

    @Autowired
    private UserRepository userRepository;

    public Fastfood createFastfood(FastfoodDTO dto) {
        Fastfood fastfood = new Fastfood();
        mapDtoToEntity(dto, fastfood);
//        fastfood.setClients(new ArrayList<>());
//        fastfood.setProduits(new ArrayList<>());
        return fastfoodRepository.save(fastfood);
    }


    public Optional<Fastfood> getFastfoodById(Long id) {
        return fastfoodRepository.findById(id);
    }

    public List<Fastfood> getAllFastfoods() {
        return fastfoodRepository.findAll();
    }

    public Fastfood updateFastfood(Long id, FastfoodDTO dto) {
        Fastfood fastfood = fastfoodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fastfood non trouvé avec l'ID : " + id));
        mapDtoToEntity(dto, fastfood);
        return fastfoodRepository.save(fastfood);
    }

    public void deleteFastfood(Long id) {
        if (!fastfoodRepository.existsById(id)) {
            throw new RuntimeException("Fastfood non trouvé avec l'ID : " + id);
        }
        fastfoodRepository.deleteById(id);
    }

    public Optional<Fastfood> findByIntitule(String intitule) {
        return fastfoodRepository.findByIntitule(intitule);
    }

    public List<Fastfood> searchByAdresse(String adresse) {
        return fastfoodRepository.findByAdresseContainingIgnoreCase(adresse);
    }

//    public Optional<Fastfood> findByMail(String mail) {
//        return fastfoodRepository.findByMail(mail);
//    }

//    public Optional<Fastfood> findByGerantId(Long gerantId) {
//        return fastfoodRepository.findByGerantId(gerantId);
//    }

//    public Optional<Fastfood> findByServeurId(Long serveurId) {
//        return fastfoodRepository.findByServeurId(serveurId);
//    }

    public List<Fastfood> searchByKeyword(String keyword) {
        return fastfoodRepository.searchByKeyword(keyword);
    }

//    public List<Fastfood> findWithClients() {
//        return fastfoodRepository.findWithClients();
//    }

//    public List<Fastfood> findByMinProduits(int minProduits) {
//        return fastfoodRepository.findByMinProduits(minProduits);
//    }

    private void mapDtoToEntity(FastfoodDTO dto, Fastfood fastfood) {
        fastfood.setIntitule(dto.getIntitule());
        fastfood.setLogoImage(dto.getLogoImage());
        fastfood.setAdresse(dto.getAdresse());
//        fastfood.setTelephone(dto.getTelephone());
//        fastfood.setMail(dto.getMail());
//        fastfood.setMotdepasse(dto.getMotdepasse());

//        if (dto.getGerantId() != null) {
//            User gerant = userRepository.findById(dto.getGerantId())
//                    .orElseThrow(() -> new RuntimeException("Gérant non trouvé avec l'ID : " + dto.getGerantId()));
//            fastfood.setGerant(gerant);
//        }
//
//        if (dto.getServeurId() != null) {
//            User serveur = userRepository.findById(dto.getServeurId())
//                    .orElseThrow(() -> new RuntimeException("Serveur non trouvé avec l'ID : " + dto.getServeurId()));
//            fastfood.setServeur(serveur);
//        }
    }

}