package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

//    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    // 게시글 등록
    public Board createBoard(Board board){
//        boardMapper.createBoard(board);
        return boardRepository.save(board);
    }

    // 게시글 삭제
    public void deleteBoard(long seq){
//        boardMapper.deleteBoard(seq);
        boardRepository.deleteById(seq);
    }

    // 게시글 수정
    public void updateBoard(Board board){
        //boardMapper.updateBoard(board);
    }

    // 게시판리스트 조회
    public List<Board> getBoardList(){
        return boardRepository.findAll();
    }

    // 게시판 상세 조회
    public Optional<Board> getBoardDetail(long seq){
        return boardRepository.findById(seq);
    }

}
