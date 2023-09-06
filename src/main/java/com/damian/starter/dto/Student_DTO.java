package com.damian.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Student_DTO implements Super_DTO, Serializable {
    private String studentId;
    private String studentName;
    private String studentAddress;
    private String studentEmail;
}
