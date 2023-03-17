package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    // 게시글 등록
    public void createBoard(Board board){
        boardMapper.createBoard(board);
    }

    // 게시글 삭제
    public void deleteBoard(long seq){
        boardMapper.deleteBoard(seq);
    }

    // 게시글 수정
    public void updateBoard(Board board){
        boardMapper.updateBoard(board);
    }

    // 게시판리스트 조회
    public List<Board> getBoardList(HashMap<String,Object> map){
        return boardMapper.getBoardList(map);
    }

    // 게시판 상세 조회
    public Board getBoardDetail(long seq){
        return boardMapper.getBoardDetail(seq);
    }

}
