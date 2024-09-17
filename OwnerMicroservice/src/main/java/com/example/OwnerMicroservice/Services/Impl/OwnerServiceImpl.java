package com.example.OwnerMicroservice.Services.Impl;

import com.example.OwnerMicroservice.Dto.Mappers.ModelDtoMapper;
import com.example.OwnerMicroservice.Dto.OwnerDto;
import com.example.OwnerMicroservice.Repository.OwnerRepositories.OwnerRepository;
import com.example.OwnerMicroservice.Services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final ModelDtoMapper mapper;
    
    @Override
    public OwnerDto saveOwner(OwnerDto ownerDto) {
        ownerRepository.save(mapper.dtoToOwnerModel(ownerDto));

        return ownerDto;
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return  ownerRepository
                .findAll()
                .stream()
                .map(mapper::ownerModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.delete(ownerRepository.getOwnerById(id));
    }

    @Override
    public OwnerDto getOwnerById(Long id) {
        return mapper.ownerModelToDto(ownerRepository.getOwnerById(id));
    }
}
