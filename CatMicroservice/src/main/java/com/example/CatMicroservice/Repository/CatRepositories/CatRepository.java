package com.example.CatMicroservice.Repository.CatRepositories;

import com.example.CatMicroservice.Models.Cat;
import com.example.CatMicroservice.Models.CatsColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {
    Cat findCatById(Long id);
    Cat findCatByIdAndOwnerName(Long id, String ownerName);
    List<Cat> findCatsByColor(CatsColor color);
    List<Cat> findCatsByColorAndOwnerName(CatsColor color, String ownerName);
    List<Cat> findCatsByOwnerId(Long id);
    List<Cat> findCatsByOwnerName(String name);
}
