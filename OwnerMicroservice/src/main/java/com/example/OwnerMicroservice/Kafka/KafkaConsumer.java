package com.example.OwnerMicroservice.Kafka;

import com.example.OwnerMicroservice.Dto.OwnerDto;
import com.example.OwnerMicroservice.Models.Owner;
import com.example.OwnerMicroservice.Repository.OwnerRepositories.OwnerRepository;
import com.example.OwnerMicroservice.Services.OwnerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {
    private final OwnerService ownerService;
    private final OwnerRepository ownerRepository;
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "check_owner_exists_request", groupId = "group")
    public void checkOwnerExists(String name) {
        Owner owner = ownerRepository.getOwnerByName(name);

        if (owner != null) {
            kafkaProducer.checkOwnerExists(owner);
        }
    }

    @KafkaListener(topics = "create_owner_request", groupId = "group")
    public void createOwner(String ownerString) {
        OwnerDto ownerDto;
        try {
            ownerDto = objectMapper.readValue(ownerString, OwnerDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ownerService.saveOwner(ownerDto);
    }

    @KafkaListener(topics = "get_all_owners_request", groupId = "group")
    public void getAllOwners() {
        StringBuilder owners = new StringBuilder();

        List<OwnerDto> ownerList = ownerService.getAllOwners();

        for (OwnerDto ownerDto : ownerList) {
            owners.append(ownerDto.toString());
        }

        String ans = "[" + owners.toString().replace("}{", "}\n,{") + "]";

        kafkaProducer.getAllOwners(ans);
    }

    @KafkaListener(topics = "get_owner_by_id_request", groupId = "group")
    public void getOwnerById(String id) {
        OwnerDto ownerDto = ownerService.getOwnerById(Long.valueOf(id));

        if (ownerDto != null) {
            kafkaProducer.getOwnerById(ownerDto);
        }
    }

    @KafkaListener(topics = "delete_owner_by_id_request", groupId = "group")
    public void deleteOwnerById(String id) {
        OwnerDto ownerDto = ownerService.getOwnerById(Long.valueOf(id));
        ownerRepository.delete(ownerRepository.getOwnerById(Long.valueOf(id)));

        if (ownerDto != null) {
            kafkaProducer.deleteOwnerById(ownerDto);
        }
    }


}
