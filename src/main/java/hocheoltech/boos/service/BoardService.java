package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Category;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.domain.MembersBoard;
import hocheoltech.boos.dto.BoardListDto;
import hocheoltech.boos.dto.UpdateBoardDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

//    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;
    private final MembersRepository membersRepository;
    private final MembersBoardRepository membersBoardRepository;
    private final CategoryRepository categoryRepository;


    // 게시글 등록
    public BoardListDto createBoard(BoardListDto boardListDto, String membersId){
        Members members = membersRepository.findById(membersId).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        String categoryName = boardListDto.getCategoryName();
        Category category = categoryRepository.findByCategoryName(categoryName).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.INCORRECT_CATEGORYNAME.getMsg()));

        Board board = Board.builder()
                .category(category)
                .writer(members.getNickname())
                .title(boardListDto.getTitle())
                .content(boardListDto.getContent())
                .build();

        MembersBoard membersBoard = MembersBoard.builder()
                                         .board(board)
                                         .members(members)
                                         .build();
        Board result = membersBoardRepository.save(membersBoard).getBoard();
        BoardListDto resultDto = BoardListDto.builder()
                .seq(result.getSeq())
                .writer(result.getWriter())
                .title(result.getTitle())
                .categoryName(result.getCategory().getCategoryName())
                .content(result.getContent())
                .regTime(result.getRegTime())
                .modifyYn(result.getModifyYn())
                .modifyTime(result.getModifyTime())
                .build();
        return resultDto;
    }

    public void deleteBoard(long boardSeq, String membersId){
        Board board = boardRepository.findBoardBySeqAndWriter(boardSeq, membersId).orElseThrow( // 해당 게시판 자체가 없는지 확인 및 영속처리
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_BOARD.getMsg()));
        boardRepository.delete(board);
    }

    // 게시글 수정
    public void updateBoard(UpdateBoardDto updateBoardDto){
        Board existBoard = boardRepository.findBoardBySeqAndWriter(updateBoardDto.getBoardSeq(),updateBoardDto.getWriter()).orElseThrow( // 해당 게시판 자체가 없는지 확인 및 영속처리
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_BOARD.getMsg()));
        existBoard.updateBoard(updateBoardDto); // 기존 보드에 값을 업데이트 ,, 더티 체킹으로 자동 업데이트 쿼리
    }

    // 게시판리스트 조회
    public Page<BoardListDto> getBoardList(BoardListDto boardListDto , Pageable pageable){
        Page<BoardListDto> boardList = boardRepository.findBoardListPaging(boardListDto, pageable);
        return boardList;
    }

    public Board getBoardDetail(long seq){
        Board board = boardRepository.findBoardWithCategory(seq).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_BOARD.getMsg()));
        return board;
    }

}
