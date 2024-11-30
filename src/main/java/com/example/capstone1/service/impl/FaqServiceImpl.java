package com.example.capstone1.service.impl;

import com.example.capstone1.domain.Faq;
import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.FaqDto;
import com.example.capstone1.mapper.FaqMapper;
import com.example.capstone1.repository.FaqRepository;
import com.example.capstone1.repository.FaqRepository;
import com.example.capstone1.repository.UserRepository;
import com.example.capstone1.service.FaqService;
import com.example.capstone1.util.FileUpload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;
    private final FaqMapper faqMapper;
    private final UserRepository userRepository;
    public FaqServiceImpl(
            FaqRepository faqRepository
            , FaqMapper faqMapper
            , UserRepository userRepository
    ) {
        this.faqRepository = faqRepository;
        this.faqMapper = faqMapper;
        this.userRepository = userRepository;
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(FaqDto.CreateReqDto param) {
        System.out.println("create");
        return faqRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(FaqDto.UpdateReqDto param) {
        System.out.println("update");
        Faq faq = faqRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException(""));
        if(param.getTitle() != null) {
            faq.setTitle(param.getTitle());
        }
        if(param.getContent() != null) {
            faq.setContent(param.getContent());
        }
        faqRepository.save(faq);
    }
    @Override
    public void delete(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(()->new RuntimeException(""));
        faqRepository.delete(faq);
    }

    public FaqDto.DetailResDto get(Long id) {
        return faqMapper.detail(id);
    }
    public List<FaqDto.DetailResDto> detailList(List<FaqDto.DetailResDto> list) {
        List<FaqDto.DetailResDto> newList = new ArrayList<>();
        for(FaqDto.DetailResDto each : list) {
            newList.add(get(each.getId()));
        }
        return newList;
    }
    @Override
    public FaqDto.DetailResDto detail(Long id) {
        return get(id);
    }
    @Override
    public List<FaqDto.DetailResDto> list(FaqDto.ListReqDto param) {
        return detailList(faqMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(FaqDto.PagedListReqDto param){
        DefaultDto.PagedListResDto retrunVal = DefaultDto.PagedListResDto.init(param, faqMapper.pagedListCount(param));
        retrunVal.setList(detailList(faqMapper.pagedList(param)));
        return retrunVal;
    }
    @Override
    public List<FaqDto.DetailResDto> scrollList(FaqDto.ScrollListReqDto param){
        param.init();
        Long cursor = param.getCursor();
        if(cursor != null){
            Faq faq = faqRepository.findById(cursor).orElseThrow(() -> new RuntimeException(""));
            param.setCreatedAt(faq.getCreatedAt() + "");
        }
        return detailList(faqMapper.scrollList(param));
    }

}
