package com.example.ApiMicroservice.Controllers;

import com.example.ApiMicroservice.Dto.OwnerDto;
import com.example.ApiMicroservice.Kafka.KafkaConsumer;
import com.example.ApiMicroservice.Kafka.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final KafkaConsumer kafkaConsumer;
    private final KafkaProducer kafkaProducer;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("get_all")
    public List<OwnerDto> findAllOwners(){
        kafkaProducer.getAllOwners();
        Optional<List<OwnerDto>> ownerDtos;
        
        try {
            ownerDtos = kafkaConsumer.getAllOwners().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }
        return ownerDtos.get();
    }
    
    @PostMapping("new_user")
    public OwnerDto saveOwner(@RequestBody OwnerDto owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        kafkaProducer.createOwner(owner);
        return owner;
    }
    
    @DeleteMapping("delete_owner/{id}")
    public OwnerDto deleteOwner(@PathVariable Long id) {
        kafkaProducer.deleteOwnerById(id);
        Optional<OwnerDto> ownerDto;
        try {
            ownerDto = kafkaConsumer.deleteOwnerById().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return ownerDto.get();
    }
    
    @GetMapping("get_owner/{id}")
    public OwnerDto getOwnerById(@PathVariable Long id) {
        kafkaProducer.getOwnerById(id);
        Optional<OwnerDto> ownerDto;
        try {
            ownerDto = kafkaConsumer.getOwnerById().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return ownerDto.get();
    }
}
