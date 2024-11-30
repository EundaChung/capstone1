package com.example.capstone1.controller;

import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.FaqDto;
import com.example.capstone1.service.FaqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/faq")
@RestController
public class FaqRestController {

    private final FaqService faqService;
    public FaqRestController(
            FaqService faqService
    ) {
        this.faqService = faqService;
    }

    /**/
    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody FaqDto.CreateReqDto param){
        return ResponseEntity.ok(faqService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody FaqDto.UpdateReqDto param){
        faqService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<String> delete(@RequestBody FaqDto.UpdateReqDto param){
        faqService.delete(param.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/detail")
    public ResponseEntity<FaqDto.DetailResDto> detail(@RequestParam Long id){
        return ResponseEntity.ok(faqService.detail(id));
    }
    @GetMapping("/list")
    public ResponseEntity<List<FaqDto.DetailResDto>> list(FaqDto.ListReqDto param){
        return ResponseEntity.ok(faqService.list(param));
    }
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(FaqDto.PagedListReqDto param){
        return ResponseEntity.ok(faqService.pagedList(param));
    }
    @GetMapping("/mlist")
    public ResponseEntity<List<FaqDto.DetailResDto>> mlist(FaqDto.ScrollListReqDto param){
        return ResponseEntity.ok(faqService.scrollList(param));
    }
}