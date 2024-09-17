package com.example.CatMicroservice.Dto;

import com.example.CatMicroservice.Models.CatsColor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class CatDto {
    private String name;
    private Date birthDate;
    private String breed;
    private CatsColor color;
    private Long ownerId;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = birthDate != null ? dateFormat.format(birthDate) : null;

        return "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"birthdate\": \"" + formattedDate + "\",\n" +
                "  \"breed\": \"" + breed + "\",\n" +
                "  \"color\": \"" + color + "\",\n" +
                "  \"ownerId\": \"" + ownerId + "\"\n" +
                "}";
    }
}
