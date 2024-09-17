package com.example.OwnerMicroservice.Services;

import com.example.OwnerMicroservice.Dto.OwnerDto;

import java.util.List;

public interface OwnerService {
    OwnerDto saveOwner(OwnerDto ownerDto);
    OwnerDto getOwnerById(Long id);
    void deleteOwner(Long ownerId);
    List<OwnerDto> getAllOwners();
}
