package com.example.recruitment.dto;

import com.example.recruitment.entity.Recruitment;
import com.example.recruitment.enums.RecruitmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class RecruitmentDto {
    public record Request(
            String title,
            Integer recruitmentCount,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
            LocalDateTime closingDate,
            String companyMemberId,
            RecruitmentStatus status
    ) {
        public Recruitment toEntity() {
            return Recruitment.builder()
                    .title(title)
                    .recruitmentCount(recruitmentCount)
                    .closingDate(closingDate)
                    .build();
        }
    }
}
