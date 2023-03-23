package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.domain.MembersBoard;
import hocheoltech.boos.dto.UpdateBoardDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.BoardRepository;
import hocheoltech.boos.repository.MembersBoardRepository;
import hocheoltech.boos.repository.MembersBoardRepositoryCustom;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

//    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;
    private final MembersRepository membersRepository;
    private final MembersBoardRepository membersBoardRepository;

    // 게시글 등록
    public Board createBoard(Board board, Long membersId){
        Members members = membersRepository.findById(membersId).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        MembersBoard membersBoard = MembersBoard.builder()
                                         .board(board)
                                         .members(members)
                                         .build();
        return membersBoardRepository.save(membersBoard).getBoard();
    }

    // 게시글 삭제
    public void deleteBoard(long seq){
        if(!boardRepository.existsById(seq)){
            throw new NoSuchElementException("일치하는 게시판이 없습니다 seq : "+seq);
        }
        boardRepository.deleteById(seq);
    }

    // 게시글 수정
    public void updateBoard(Long membersSeq, Long boardSeq, UpdateBoardDto updateBoardDto){
        Board existBoard = boardRepository.findById(boardSeq).orElseThrow( // 해당 게시판 자체가 없는지 확인 및 영속처리
                () -> new NoSuchElementException("일치하는 게시판이 없습니다 seq : " + boardSeq));
        UpdateBoardDto updatedBoard = membersBoardRepository.findMembersBoard(membersSeq, boardSeq); // DB에 있는값 이걸 업데이트를 쳐줘야함,
        if(updatedBoard == null){ // 본인 게시물이 아닌경우
            throw new IllegalArgumentException("해당 게시물에 대해 수정 권한이 없습니다.");
        }
        updatedBoard.setUpdateDto(updateBoardDto); // 수정값으로 업데이트처리
        existBoard.updateBoard(updatedBoard); // 기존 보드에 값을 업데이트 ,, 더티 체킹으로 자동 쿼리
    }

    // 게시판리스트 조회
    public List<Board> getBoardList(){
        return boardRepository.findAll();
    }

    // 게시판 상세 조회
    public Board getBoardDetail1(long seq){
        Board board = boardRepository.findById(seq).orElseThrow(
                () -> new NoSuchElementException("일치하는 게시판이 없습니다. seq : " + seq)
        );
        return board;
    }

    public Board getBoardDetail(long seq){
        Board board = boardRepository.findBoardWithCategory(seq);
        if(board == null){
            throw new NoSuchElementException("일치하는 게시판이 없습니다 seq : "+seq);
        }
        return board;
    }

}
