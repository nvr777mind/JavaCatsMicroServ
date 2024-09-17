package com.example.ApiMicroservice.Config;

import com.example.ApiMicroservice.Dto.OwnerDto;
import com.example.ApiMicroservice.Kafka.KafkaConsumer;
import com.example.ApiMicroservice.Kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    
    private final KafkaConsumer kafkaConsumer;
    private final KafkaProducer kafkaProducer;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        kafkaProducer.checkOwnerExists(username);
        
        try {
            Optional<OwnerDto> owner = kafkaConsumer.getOwnerFuture().get(10, TimeUnit.SECONDS);
            return owner.map(MyUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve user information", e);
        }
    }
}
