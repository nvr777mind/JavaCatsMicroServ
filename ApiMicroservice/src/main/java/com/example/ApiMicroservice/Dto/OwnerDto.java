package com.example.ApiMicroservice.Dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class OwnerDto {
    private String name;
    private Date birthDate;
    private String password;
    private String roles;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = birthDate != null ? dateFormat.format(birthDate) : null;

        return "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"birthdate\": \"" + formattedDate + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"role\": \"" + roles + "\"\n" +
                "}";

    }
}
