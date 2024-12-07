package com.example.capstone1.service;

import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    /**/
    DefaultDto.CreateResDto create(PostDto.CreateReqDto param);
    void update(PostDto.UpdateReqDto param);
    void delete(Long id);
    void deletes(DefaultDto.DeletesReqDto param);
    PostDto.DetailResDto detail(Long id);
    List<PostDto.DetailResDto> list(PostDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PostDto.PagedListReqDto param);
    List<PostDto.DetailResDto> scrollList(PostDto.ScrollListReqDto param);

}
