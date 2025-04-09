package io.reflectoring.bbackend.dto;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
} 