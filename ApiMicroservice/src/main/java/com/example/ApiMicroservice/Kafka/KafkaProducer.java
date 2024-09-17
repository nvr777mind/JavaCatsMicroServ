package com.example.ApiMicroservice.Kafka;

import com.example.ApiMicroservice.Dto.CatDto;
import com.example.ApiMicroservice.Dto.OwnerDto;
import com.example.ApiMicroservice.Models.CatsColor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
@AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void checkOwnerExists(String name) {
        kafkaTemplate.send("check_owner_exists_request", name);
    }

    public void createOwner(OwnerDto ownerDto) {
        kafkaTemplate.send("create_owner_request", String.valueOf(ownerDto));
    }

    public void getAllOwners() {
        kafkaTemplate.send("get_all_owners_request", "");
    }

    public void getOwnerById(Long id) {
        kafkaTemplate.send("get_owner_by_id_request", String.valueOf(id));
    }

    public void deleteOwnerById(Long id) {
        kafkaTemplate.send("delete_owner_by_id_request", String.valueOf(id));
    }

    public void getAllCats(String name) {
        kafkaTemplate.send("get_all_cats_request", name);
    }

    public void createCat(CatDto catDto) {
        kafkaTemplate.send("create_cat_request", String.valueOf(catDto));
    }

    public void deleteCatById(Long id) {
        kafkaTemplate.send("delete_cat_by_id_request", String.valueOf(id));
    }

    public void getCatById(Long id) {
        kafkaTemplate.send("get_cat_by_id_request", String.valueOf(id));
    }

    public void getCatsByColor(CatsColor color) {
        kafkaTemplate.send("get_cat_by_color_request", String.valueOf(color));
    }

    public void getOwnerCats(Long id) {
        kafkaTemplate.send("get_cat_list_request", String.valueOf(id));
    }
}
