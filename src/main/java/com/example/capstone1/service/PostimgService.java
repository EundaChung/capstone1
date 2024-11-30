package com.example.capstone1.service;

import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.PostimgDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostimgService {
    /**/
    PostimgDto.CreateResDto create(PostimgDto.CreateReqDto param);
    void update(PostimgDto.UpdateReqDto param);
    void delete(Long id);
    void deletes(DefaultDto.DeletesReqDto param);
    PostimgDto.DetailResDto detail(Long id);
    List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PostimgDto.PagedListReqDto param);
    List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto param);

}
