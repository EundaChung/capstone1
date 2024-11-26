package com.example.capstone1.service;

import com.example.capstone1.domain.Board;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BoardService {
    Map<String, Object> createBoard(Map<String, Object> params);
    Map<String, Object> updateBoard(Map<String, Object> params);
    Map<String, Object> deleteBoard(Integer id);
    List<Board> listBoard();
    Board detailBoard(Integer id);
}
