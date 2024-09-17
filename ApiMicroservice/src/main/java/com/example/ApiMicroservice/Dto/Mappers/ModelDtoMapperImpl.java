package com.example.ApiMicroservice.Dto.Mappers;

import com.example.ApiMicroservice.Dto.CatDto;
import com.example.ApiMicroservice.Dto.OwnerDto;
import com.example.ApiMicroservice.Models.Cat;
import com.example.ApiMicroservice.Models.Owner;
import org.springframework.stereotype.Component;

@Component
public class ModelDtoMapperImpl implements ModelDtoMapper {
    
    @Override
    public CatDto catModelToDto(Cat cat) {
        CatDto catDto = new CatDto();

        catDto.setName(cat.getName());
        catDto.setColor(cat.getColor());
        catDto.setBirthDate(cat.getBirthDate());
        catDto.setBreed(cat.getBreed());
        
        if (cat.getOwner() == null) { catDto.setOwnerId(null); }
        else { catDto.setOwnerId(cat.getOwner().getId()); }

        return catDto;
    }

    @Override
    public OwnerDto ownerModelToDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        
        ownerDto.setName(owner.getName());
        ownerDto.setBirthDate(owner.getBirthDate());
        ownerDto.setPassword(owner.getPassword());
        ownerDto.setRoles(owner.getRoles());
        
        return ownerDto;
    }
}
