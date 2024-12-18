package com.example.recruitment.controller;

import com.example.recruitment.dto.ResumeDto;
import com.example.recruitment.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    //지원자가 이력서를 등록
    @PostMapping("/resumes")
    public Long postingResume(@RequestBody ResumeDto.Request request) {
        return resumeService.postResume(request);
    }

    //지원자가 자신이 등록한 모든 이력서를 조회
    @GetMapping("/resumes")
    public List<ResumeDto.Response> getResumeList(@RequestParam String memberLoginId) {
        return resumeService.getResumeList(memberLoginId);
    }

    //지원자가 특정 아이디의 이력서를 조회
    @GetMapping("/resumes/{id}")
    public ResumeDto.Response getResume(@PathVariable Long id) {
        return resumeService.getResume(id);
    }

    //지원자가 특정 아이디의 이력서를 수정
    @PutMapping("/resumes/{id}")
    public ResumeDto.Response getResume(@PathVariable Long id,
                                        @RequestBody ResumeDto.Request request) {
        return resumeService.modifyResume(id, request);
    }

    //지원자가 특정 아이디의 이력서를 삭제
    @DeleteMapping("/resumes/{id}")
    public String deleteResume(@PathVariable Long id, @RequestParam String memberLoginId) {
        resumeService.deleteResume(id, memberLoginId);
        return "삭제 완료";
    }
}
