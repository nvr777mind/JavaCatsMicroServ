package com.example.ApiMicroservice.Dto.Mappers;

import com.example.ApiMicroservice.Dto.CatDto;
import com.example.ApiMicroservice.Dto.OwnerDto;
import com.example.ApiMicroservice.Models.Cat;
import com.example.ApiMicroservice.Models.Owner;

public interface ModelDtoMapper {
    CatDto catModelToDto(Cat cat);
    OwnerDto ownerModelToDto(Owner owner);
}
