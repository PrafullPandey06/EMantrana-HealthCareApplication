package com.example.emantrana.Admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ViewPatientRequestDTO {

    private String preseciptionDate;
    private String day;

    private String email;

    public  ViewPatientRequestDTO(
            @JsonProperty("preseciptionDate")String preseciptionDate,
            @JsonProperty("day")String day
    ) {

        this.preseciptionDate = preseciptionDate;
        this.day = day;

    }
}
