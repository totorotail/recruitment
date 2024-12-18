package com.example.recruitment.controller;

import com.example.recruitment.dto.RecruitmentDto;
import com.example.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    //기업 멤버가 채용 공고를 등록
    @PostMapping("/recruitments")
    public void postingRecruitment(@RequestBody RecruitmentDto.Request request) {
        recruitmentService.postingRecruitment(request);
    }

    //모집중인 모든 공고 리스트
    @GetMapping("/recruitments")
    public List<RecruitmentDto.Response> getRecruitmentList() {
        return recruitmentService.getRecruitmentList();
    }

    //특정 아이디의 공고 확인
    @GetMapping("/recruitments/{id}")
    public RecruitmentDto.Response getRecruitment(@PathVariable Long id) {
        return recruitmentService.getRecruitment(id);
    }

    //특정 아이디의 공고 정보를 변경
    @PutMapping("/recruitments/{id}")
    public RecruitmentDto.Response getRecruitment(@PathVariable Long id,
                                                  @RequestBody RecruitmentDto.Request request) {
        return recruitmentService.modifyRecruitment(id, request);
    }

}
