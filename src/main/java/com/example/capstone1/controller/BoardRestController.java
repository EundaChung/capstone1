package com.example.capstone1.controller;

import com.example.capstone1.domain.Board;
import com.example.capstone1.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/api/board")
@RestController
public class BoardRestController {

    private final BoardService boardService;
    public BoardRestController(
            BoardService boardService
    ){
        this.boardService = boardService;
    }

    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> params) {
    return boardService.createBoard(params);
}

    @GetMapping("/list")
    public List<Board> list(){

        return boardService.listBoard();
}
    @GetMapping("/detail")
    public Board detail(@RequestParam Integer id){

        return boardService.detailBoard(id);
    }

    @GetMapping("/update")
    public Map<String, Object> update(@RequestParam Map<String, Object> params){
        return boardService.updateBoard(params);
    }
    @GetMapping("/delete")
    public Map<String, Object> delete(@RequestParam Map<String, Object> params){
        return boardService.deleteBoard(Integer.parseInt(params.get("id") + ""));
    }
}
