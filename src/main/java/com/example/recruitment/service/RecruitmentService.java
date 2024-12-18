package com.example.recruitment.service;

import com.example.recruitment.dto.RecruitmentDto;
import com.example.recruitment.entity.CompanyMember;
import com.example.recruitment.entity.Recruitment;
import com.example.recruitment.enums.RecruitmentStatus;
import com.example.recruitment.repository.CompanyMemberRepository;
import com.example.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyMemberRepository companyMemberRepository;

    @Transactional
    public void postingRecruitment(RecruitmentDto.Request request) {
        CompanyMember companyMember = companyMemberRepository.findByLoginId(request.companyMemberId())
                .orElseThrow(() -> new RuntimeException("기업 회원 정보 없음"));

        Recruitment recruitment = request.toEntity();
        recruitment.setCompanyMember(companyMember);
        recruitment.opening();

        recruitmentRepository.save(recruitment);
    }

    @Transactional(readOnly = true)
    public List<RecruitmentDto.Response> getRecruitmentList() {
        List<Recruitment> recruitmentList = recruitmentRepository.findAllByStatus(RecruitmentStatus.OPEN);
        return recruitmentList.stream().map(Recruitment::toDto).toList();
    }

    @Transactional(readOnly = true)
    public RecruitmentDto.Response getRecruitment(Long id) {
        return recruitmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당하는 공고 없음")).toDto();
    }

    @Transactional
    public RecruitmentDto.Response modifyRecruitment(Long id, RecruitmentDto.Request request) {
        Recruitment recruitment = recruitmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당하는 공고 없음"));

        if (!Objects.equals(recruitment.getCompanyMember().getLoginId(), request.companyMemberId())) {
            throw new RuntimeException("잘못된 기업회원 정보 입니다");
        }

        return recruitment.update(request).toDto();
    }

    @Transactional
    public void deleteRecruitment(Long id, String companyLoginId) {
        Recruitment recruitment = recruitmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당하는 공고 없음"));

        if (!Objects.equals(recruitment.getCompanyMember().getLoginId(), companyLoginId)) {
            throw new RuntimeException("잘못된 기업회원 정보 입니다");
        }

        recruitmentRepository.delete(recruitment);
    }


}
