package com.example.OwnerMicroservice.Dto.Mappers;

import com.example.OwnerMicroservice.Dto.OwnerDto;
import com.example.OwnerMicroservice.Models.Owner;

public interface ModelDtoMapper {
    OwnerDto ownerModelToDto(Owner owner);
    Owner dtoToOwnerModel(OwnerDto ownerDto);
}
