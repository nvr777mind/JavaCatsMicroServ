package com.example.CatMicroservice.Services;

import com.example.CatMicroservice.Dto.CatDto;
import com.example.CatMicroservice.Models.Cat;
import com.example.CatMicroservice.Models.CatsColor;

import java.util.List;

public interface CatService {
    List<CatDto> findAllCats();
    CatDto saveCat(CatDto catDto);
    CatDto findCat(Long id);
    void deleteCat(Long id);
    List<CatDto> findCatsByColor(CatsColor color);
    void makeCatsFriends(Long firstCatId, Long secondCatId);
    List<CatDto> getCatListById(Long id);
}
