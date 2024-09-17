package com.example.OwnerMicroservice.Repository.OwnerRepositories;

import com.example.OwnerMicroservice.Models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner getOwnerById(Long id);
    Owner getOwnerByName(String name);
}
