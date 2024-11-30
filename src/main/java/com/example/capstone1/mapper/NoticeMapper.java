package com.example.capstone1.mapper;

import com.example.capstone1.dto.NoticeDto;

import java.util.List;

public interface NoticeMapper {
    NoticeDto.DetailResDto detail(Long id);
    List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto param);
    List<NoticeDto.DetailResDto> pagedList(NoticeDto.PagedListReqDto param);
    int pagedListCount(NoticeDto.PagedListReqDto param);
    List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto param);
}