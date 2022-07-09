package com.example.enigmashop.security.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private Integer status;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Set<String> role;
}
