package com.example.emantrana.Admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class followupdto {

    private String Fname;


    private String Lname;

    private String phoneNumber;

    public followupdto(
            @JsonProperty("Fname") String fname,
            @JsonProperty("Lname") String lname,
            @JsonProperty("phoneNumber") String phoneNumber) {
        this.Fname = fname;
        this.Lname = lname;
        this.phoneNumber = phoneNumber;
    }
}
