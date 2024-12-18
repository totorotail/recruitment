package com.example.recruitment.entity;

import com.example.recruitment.dto.RecruitmentDto;
import com.example.recruitment.enums.RecruitmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private Long id;
    private String title;
    private Integer recruitmentCount;
    private LocalDateTime closingDate;
    @Enumerated(EnumType.STRING)
    private RecruitmentStatus status;
    @UpdateTimestamp
    private LocalDateTime modifyDate;
    @CreationTimestamp
    private LocalDateTime postingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_member_id")
    private CompanyMember companyMember;

    @Builder
    public Recruitment(
            String title,
            Integer recruitmentCount,
            LocalDateTime closingDate
    ) {
        this.title = title;
        this.recruitmentCount = recruitmentCount;
        this.closingDate = closingDate;
    }

    public void opening() {
        this.status = RecruitmentStatus.OPEN;
    }

    public RecruitmentDto.Response toDto() {
        return RecruitmentDto.Response.builder()
                .id(this.id)
                .title(this.title)
                .recruitmentCount(this.recruitmentCount)
                .closingDate(this.closingDate)
                .status(this.status)
                .modifyDate(this.modifyDate)
                .postingDate(this.postingDate)
                .companyMemberId(this.companyMember.getId())
                .companyName(this.companyMember.getCompanyName())
                .build();
    }
}
