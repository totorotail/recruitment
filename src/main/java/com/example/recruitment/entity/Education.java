package com.example.recruitment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String school;
    private Integer degree; // 고졸: 0, 대졸: 1, 석사: 2, 박사: 3
}
