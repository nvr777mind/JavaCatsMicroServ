package com.example.CatMicroservice.Dto.Mappers;

import com.example.CatMicroservice.Dto.CatDto;
import com.example.CatMicroservice.Models.Cat;

public interface ModelDtoMapper {
    CatDto catModelToDto(Cat cat);
    Cat dtoToCatModel(CatDto catDto);
}
