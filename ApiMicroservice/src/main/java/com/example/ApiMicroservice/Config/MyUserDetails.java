package com.example.ApiMicroservice.Config;

import com.example.ApiMicroservice.Dto.OwnerDto;
import com.example.ApiMicroservice.Models.Owner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    
    private OwnerDto owner;
    
    public MyUserDetails(OwnerDto owner){
        this.owner = owner;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays
                .stream(owner.getRoles().split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return owner.getPassword();
    }

    @Override
    public String getUsername() {
        return owner.getName();
    }
}
