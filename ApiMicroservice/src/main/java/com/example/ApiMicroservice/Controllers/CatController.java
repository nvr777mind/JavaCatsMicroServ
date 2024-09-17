package com.example.ApiMicroservice.Controllers;

import com.example.ApiMicroservice.Dto.CatDto;
import com.example.ApiMicroservice.Kafka.KafkaConsumer;
import com.example.ApiMicroservice.Kafka.KafkaProducer;
import com.example.ApiMicroservice.Models.CatsColor;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/api/cats")
@AllArgsConstructor
public class CatController {
    
    private final KafkaProducer kafkaProducer;
    private final KafkaConsumer kafkaConsumer;

    @GetMapping("get_all")
    public List<CatDto> findAllCats(Principal principal){
        kafkaProducer.getAllCats(principal.getName());
        Optional<List<CatDto>> catDtos;
        try {
            catDtos = kafkaConsumer.getAllCats().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }
        
        return catDtos.get();
    }

    @GetMapping("get_cats/{color}")
    public List<CatDto> findCatsByColor(@PathVariable CatsColor color){
        kafkaProducer.getCatsByColor(color);
        Optional<List<CatDto>> catDtos;
        try {
            catDtos = kafkaConsumer.getCatsByColor().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return catDtos.get();
    }

    @PostMapping("save_cat")
    public CatDto saveCat(@RequestBody CatDto catDto) {
        kafkaProducer.createCat(catDto);
        
        return catDto;
    }
 
    @GetMapping("get_cat/{id}")
    public CatDto findCat(@PathVariable Long id) {
        kafkaProducer.getCatById(id);
        Optional<CatDto> catDto;
        try {
            catDto = kafkaConsumer.getCatById().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return catDto.get();
    }

    @DeleteMapping("delete_cat/{id}")
    public CatDto deleteCat(@PathVariable Long id) {
        kafkaProducer.deleteCatById(id);
        Optional<CatDto> catDto;
        try {
            catDto = kafkaConsumer.deleteCatById().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return catDto.get();
    }

    @GetMapping("cat_list/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public List<CatDto> getOwnerCats(@PathVariable Long id) {
        kafkaProducer.getOwnerCats(id);
        Optional<List<CatDto>> catDtos;
        try {
            catDtos = kafkaConsumer.getOwnerCats().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return catDtos.get();
    }
    
    /*@PutMapping("make_friends/{firstCatId}_{secondCatId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void makeFriends(@PathVariable Long firstCatId, @PathVariable Long secondCatId) {
        catService.makeCatsFriends(firstCatId, secondCatId);
    }*/
}