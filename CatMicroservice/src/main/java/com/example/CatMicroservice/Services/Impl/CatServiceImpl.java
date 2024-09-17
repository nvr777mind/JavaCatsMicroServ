package com.example.CatMicroservice.Services.Impl;

import com.example.CatMicroservice.Repository.CatRepositories.CatRepository;
import com.example.CatMicroservice.Services.CatService;
import com.example.CatMicroservice.Dto.CatDto;
import com.example.CatMicroservice.Dto.Mappers.ModelDtoMapper;
import com.example.CatMicroservice.Models.Cat;
import com.example.CatMicroservice.Models.CatsColor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class CatServiceImpl implements CatService {
    
    private final CatRepository catRepository;
    private final ModelDtoMapper mapper;


    @Override
    public List<CatDto> findAllCats() {
        return catRepository
                .findAll()
                .stream()
                .map(mapper::catModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CatDto> findCatsByColor(CatsColor color) {
        return catRepository
                .findCatsByColor(color)
                .stream()
                .map(mapper::catModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void makeCatsFriends(Long firstCatId, Long secondCatId) {
        Cat firstCat = catRepository.findCatById(firstCatId);
        Cat secondCat = catRepository.findCatById(secondCatId);

        firstCat.addFriend(secondCat);
        secondCat.addFriend(firstCat);

        catRepository.save(firstCat);
        catRepository.save(secondCat);
    }

    @Override
    public List<CatDto> getCatListById(Long id) {
        return catRepository
                .findCatsByOwnerId(id)
                .stream()
                .map(mapper::catModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CatDto saveCat(CatDto catDto) {
        catRepository.save(mapper.dtoToCatModel(catDto));

        return catDto;
    }

    @Override
    public CatDto findCat(Long id) {
        return mapper.catModelToDto(catRepository.findCatById(id));
    }

    @Override
    public void deleteCat(Long id) {
        catRepository.delete(catRepository.findCatById(id));
    }
}
