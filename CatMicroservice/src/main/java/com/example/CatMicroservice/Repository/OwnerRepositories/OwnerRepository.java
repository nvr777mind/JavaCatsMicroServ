package com.example.CatMicroservice.Repository.OwnerRepositories;

import com.example.CatMicroservice.Models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findOwnerById(Long id);
    Owner findOwnerByName(String name);
}
