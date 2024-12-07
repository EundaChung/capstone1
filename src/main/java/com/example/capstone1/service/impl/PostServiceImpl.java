package com.example.capstone1.service.impl;

import com.example.capstone1.domain.Post;
import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.PostDto;
import com.example.capstone1.dto.PostimgDto;
import com.example.capstone1.mapper.PostMapper;
import com.example.capstone1.repository.PostRepository;
import com.example.capstone1.service.PostService;
import com.example.capstone1.service.PostimgService;
import com.example.capstone1.util.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostimgService postimgService;
    private final PostMapper postMapper;
    public PostServiceImpl(
            PostRepository postRepository
            , PostimgService postimgService
            , PostMapper postMapper
    ) {
        this.postRepository = postRepository;
        this.postimgService = postimgService;
        this.postMapper = postMapper;
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(PostDto.CreateReqDto param) {
        System.out.println("create");
        DefaultDto.CreateResDto res = postRepository.save(param.toEntity()).toCreateResDto();

        List<MultipartFile> imgs = param.getImgs();
        if(imgs != null && !imgs.isEmpty()) {
            for(MultipartFile each : imgs){
                postimgService.create(PostimgDto.CreateReqDto.builder().postId(res.getId()).imgfile(each).build());
            }
        }

        return res;
    }
    @Override
    public void update(PostDto.UpdateReqDto param) {
        System.out.println("update");
        Post post = postRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException(""));
        if(param.getDeleted() != null) {
            post.setDeleted(param.getDeleted());
        }
        if(param.getTitle() != null) {
            post.setTitle(param.getTitle());
        }
        if(param.getContent() != null) {
            post.setContent(param.getContent());
        }
        postRepository.save(post);
    }
    @Override
    public void delete(Long id) {
        update(PostDto.UpdateReqDto.builder().id(id).deleted(true).build());
    }
    @Override
    public void deletes(DefaultDto.DeletesReqDto param) {
        for(Long id : param.getIds()){
            delete(id);
        }
    }

    public PostDto.DetailResDto get(Long id) {
        PostDto.DetailResDto res = postMapper.detail(id);
        if(res == null) { throw new RuntimeException("no data"); }
        res.setImgs(postimgService.list(PostimgDto.ListReqDto.builder().deleted(false).postId(res.getId()).build()));
        return res;
    }
    public List<PostDto.DetailResDto> detailList(List<PostDto.DetailResDto> list) {
        List<PostDto.DetailResDto> newList = new ArrayList<>();
        for(PostDto.DetailResDto each : list) {
            newList.add(get(each.getId()));
        }
        return newList;
    }
    @Override
    public PostDto.DetailResDto detail(Long id) {
        return get(id);
    }
    @Override
    public List<PostDto.DetailResDto> list(PostDto.ListReqDto param) {
        return detailList(postMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PostDto.PagedListReqDto param){
        DefaultDto.PagedListResDto retrunVal = DefaultDto.PagedListResDto.init(param, postMapper.pagedListCount(param));
        retrunVal.setList(detailList(postMapper.pagedList(param)));
        return retrunVal;
    }
    @Override
    public List<PostDto.DetailResDto> scrollList(PostDto.ScrollListReqDto param){
        param.init();
        Long cursor = param.getCursor();
        if(cursor != null){
            Post post = postRepository.findById(cursor).orElseThrow(() -> new RuntimeException(""));
            param.setCreatedAt(post.getCreatedAt() + "");
        }
        return detailList(postMapper.scrollList(param));
    }

}
