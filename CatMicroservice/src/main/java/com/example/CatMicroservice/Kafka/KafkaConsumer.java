package com.example.CatMicroservice.Kafka;

import com.example.CatMicroservice.Dto.CatDto;
import com.example.CatMicroservice.Models.CatsColor;
import com.example.CatMicroservice.Models.Owner;
import com.example.CatMicroservice.Repository.OwnerRepositories.OwnerRepository;
import com.example.CatMicroservice.Services.CatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumer {
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;
    private final CatService catService;
    private final OwnerRepository ownerRepository;

    @KafkaListener(topics = "get_all_cats_request", groupId = "group")
    public void getAllCats(String name) {
        StringBuilder cats = new StringBuilder();

        Owner owner = ownerRepository.findOwnerByName(name);
        List<CatDto> catDtoList = catService.getCatListById(owner.getId());

        for (CatDto catDto : catDtoList) {
            cats.append(catDto.toString());
        }

        String ans = "[" + cats.toString().replace("}{", "}\n,{") + "]";

        kafkaProducer.getAllCats(ans);
    }

    @KafkaListener(topics = "create_cat_request", groupId = "group")
    public void createCat(String catString) {
        CatDto catDto;
        try {
            catDto = objectMapper.readValue(catString, CatDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        catService.saveCat(catDto);
    }

    @KafkaListener(topics = "delete_cat_by_id_request", groupId = "group")
    public void deleteCatById(String id) {
        CatDto catDto = catService.findCat(Long.valueOf(id));
        catService.deleteCat(Long.valueOf(id));

        if (catDto != null) {
            kafkaProducer.deleteCatById(catDto);
        }
    }

    @KafkaListener(topics = "get_cat_by_id_request", groupId = "group")
    public void getCatById(String id) {
        CatDto catDto = catService.findCat(Long.valueOf(id));

        if (catDto != null) {
            kafkaProducer.getCatById(catDto);
        }
    }

    @KafkaListener(topics = "get_cat_by_color_request", groupId = "group")
    public void getCatsByColor(CatsColor color) {
        StringBuilder cats = new StringBuilder();

        List<CatDto> catDtoList = catService.findCatsByColor(color);

        for (CatDto catDto : catDtoList) {
            cats.append(catDto.toString());
        }

        String ans = "[" + cats.toString().replace("}{", "}\n,{") + "]";

        kafkaProducer.getCatsByColor(ans);
    }

    @KafkaListener(topics = "get_cat_list_request", groupId = "group")
    public void getOwnerCats(String id) {
        StringBuilder cats = new StringBuilder();

        List<CatDto> catDtoList = catService.getCatListById(Long.valueOf(id));

        for (CatDto catDto : catDtoList) {
            cats.append(catDto.toString());
        }

        String ans = "[" + cats.toString().replace("}{", "}\n,{") + "]";

        kafkaProducer.getOwnerCats(ans);
    }
}
