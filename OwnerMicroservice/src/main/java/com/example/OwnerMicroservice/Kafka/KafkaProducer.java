package com.example.OwnerMicroservice.Kafka;

import com.example.OwnerMicroservice.Dto.Mappers.ModelDtoMapper;
import com.example.OwnerMicroservice.Dto.OwnerDto;
import com.example.OwnerMicroservice.Models.Owner;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ModelDtoMapper ownerMapper;

    public void checkOwnerExists(Owner owner) {
        kafkaTemplate.send("check_owner_exists_response", String.valueOf(ownerMapper.ownerModelToDto(owner)));
    }

    public void getAllOwners(String owners) {
        kafkaTemplate.send("get_all_owners_response", owners);
    }

    public void getOwnerById(OwnerDto ownerDto) {
        kafkaTemplate.send("get_owner_by_id_response", String.valueOf(ownerDto));
    }

    public void deleteOwnerById(OwnerDto ownerDto) {
        kafkaTemplate.send("delete_owner_by_id_response", String.valueOf(ownerDto));
    }
}
