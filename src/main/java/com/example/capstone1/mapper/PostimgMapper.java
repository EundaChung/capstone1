package com.example.capstone1.mapper;

import com.example.capstone1.dto.PostimgDto;

import java.util.List;

public interface PostimgMapper {
    PostimgDto.DetailResDto detail(Long id);
    List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto param);
    List<PostimgDto.DetailResDto> pagedList(PostimgDto.PagedListReqDto param);
    int pagedListCount(PostimgDto.PagedListReqDto param);
    List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto param);
}