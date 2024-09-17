package com.example.OwnerMicroservice.Dto.Mappers;

import com.example.OwnerMicroservice.Dto.OwnerDto;
import com.example.OwnerMicroservice.Models.Owner;
import org.springframework.stereotype.Component;

@Component
public class ModelDtoMapperImpl implements ModelDtoMapper {
    
    

    @Override
    public OwnerDto ownerModelToDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        
        ownerDto.setName(owner.getName());
        ownerDto.setBirthDate(owner.getBirthDate());
        ownerDto.setPassword(owner.getPassword());
        ownerDto.setRoles(owner.getRoles());
        
        return ownerDto;
    }

    @Override
    public Owner dtoToOwnerModel(OwnerDto ownerDto) {
        Owner owner = new Owner();
        
        owner.setName(ownerDto.getName());
        owner.setBirthDate(ownerDto.getBirthDate());
        owner.setPassword(ownerDto.getPassword());
        owner.setRoles(ownerDto.getRoles());
        
        return owner;
    }

}
