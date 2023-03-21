package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.exception.DuplicateMemberIdException;
import hocheoltech.boos.exception.NotExistBoard;
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
        Board savedBoard = boardRepository.save(board);
        return savedBoard;
    }

    // 게시글 삭제
    public void deleteBoard(long seq){
        if(!boardRepository.existsById(seq)){
            throw new NotExistBoard("There are no board matching the seq : "+seq);
        }
        boardRepository.deleteById(seq);
    }

    // 게시글 수정
    public void updateBoard(Long seq, Board board){
        if(!boardRepository.existsById(seq)){
            throw new NotExistBoard("There are no board matching the seq : "+seq);
        }
        Board existBoard = boardRepository.findById(seq).get();
        existBoard.updateBoard(board.getTitle(),board.getContent(),board.getCategory()); // 영속성 프레임워크에 의한 자동 update
    }

    // 게시판리스트 조회
    public List<Board> getBoardList(){
        return boardRepository.findAll();
    }

    // 게시판 상세 조회
    public Board getBoardDetail(long seq){
        if(!boardRepository.existsById(seq)){
            throw new NotExistBoard("There are no board matching the seq : "+seq);
        }
        return boardRepository.findById(seq).get();
    }

}
