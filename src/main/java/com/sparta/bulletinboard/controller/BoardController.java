package com.sparta.bulletinboard.controller;

import com.sparta.bulletinboard.dto.BoardRequestDto;
import com.sparta.bulletinboard.dto.BoardResponseDto;
import com.sparta.bulletinboard.entity.Board;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final Map<Long, Board> boardList = new HashMap<>();

    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        //RequestDto -> Entity로 저장해야함!
        Board board = new Board(requestDto);


        //Board Max Id찾기
        Long maxId = boardList.size() > 0 ? Collections.max(boardList.keySet()) + 1 : 1;
        board.setId(maxId); //???vlfdygksrj???

        //DB저장
        boardList.put(board.getId(), board);

        //Entity -> ResponseDto
        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        return boardResponseDto;
    }

    @GetMapping("/board")
    public List<BoardResponseDto> getBoard() {
        // Map to List
        List<BoardResponseDto> responseList = boardList.values().stream()
                .map(BoardResponseDto::new).toList();

        return responseList;

    }

}
