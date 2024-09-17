package com.example.CatMicroservice.Kafka;

import com.example.CatMicroservice.Dto.CatDto;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void getAllCats(String cats) {
        kafkaTemplate.send("get_all_cats_response", cats);
    }

    public void deleteCatById(CatDto catDto) {
        kafkaTemplate.send("delete_cat_by_id_request", String.valueOf(catDto));
    }

    public void getCatById(CatDto catDto) {
        kafkaTemplate.send("get_cat_by_id_response", String.valueOf(catDto));
    }

    public void getCatsByColor(String cats) {
        kafkaTemplate.send("get_cat_by_color_response", cats);
    }

    public void getOwnerCats(String cats) {
        kafkaTemplate.send("get_cat_list_response", cats);
    }
}
