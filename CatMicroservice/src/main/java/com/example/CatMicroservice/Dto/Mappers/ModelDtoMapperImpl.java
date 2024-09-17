package com.example.CatMicroservice.Dto.Mappers;

import com.example.CatMicroservice.Dto.CatDto;
import com.example.CatMicroservice.Models.Cat;
import com.example.CatMicroservice.Repository.OwnerRepositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ModelDtoMapperImpl implements ModelDtoMapper {

    private final OwnerRepository ownerRepository;
    
    @Override
    public CatDto catModelToDto(Cat cat) {
        CatDto catDto = new CatDto();

        catDto.setName(cat.getName());
        catDto.setBreed(cat.getBreed());
        catDto.setColor(cat.getColor());
        catDto.setBirthDate(cat.getBirthDate());
        catDto.setOwnerId(cat.getOwner().getId());

        return catDto;
    }

    @Override
    public Cat dtoToCatModel(CatDto catDto) {
        Cat cat = new Cat();

        cat.setName(catDto.getName());
        cat.setBreed(catDto.getBreed());
        cat.setColor(catDto.getColor());
        cat.setOwner(ownerRepository.findOwnerById(catDto.getOwnerId()));
        cat.setBirthDate(catDto.getBirthDate());

        return cat;
    }
}
