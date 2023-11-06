package com.sparta.bulletinboard.controller;

import com.sparta.bulletinboard.dto.BoardRequestDto;
import com.sparta.bulletinboard.dto.BoardResponseDto;
import com.sparta.bulletinboard.entity.Board;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        board.setId(maxId);

        //DB저장
        boardList.put(board.getId(), board);

//        //날짜 저장하기
//        LocalDate todaydate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        String fommatedTodaydate = todaydate.format(formatter);
//        board.setDate(fommatedTodaydate);


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

    @PutMapping("/board/{id}")
    public Long modifyBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        //해당 메모가 디비에 존재하는지 확인
        if (boardList.containsKey(id)) {
            //해당 메모가 존재하면 가져오기
            Board board = boardList.get(id);

            //해당 글 수정
            board.modify(requestDto);

            return board.getId();
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }


    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id, @RequestParam("password") int password) {
        //해당 메모가 DB에 존재하는지 확인
        if (boardList.containsKey(id)) {
            Board board = boardList.get(id);
            if (password == board.getPassword()) {
                boardList.remove(id);
                return id;
            } else {
                throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
            }
        }
            else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }


}
