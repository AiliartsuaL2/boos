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

    public void deleteBoard(long membersSeq, long boardSeq){
        MembersBoard membersBoard = membersBoardRepository.findMembersBoardByMembersSeqAndBoardSeq(membersSeq, boardSeq);
        if(membersBoard == null){
            throw new NoSuchElementException(ErrorMessage.UNAUTHORIZED_PERMISSION.getMsg());
        }
        membersBoardRepository.delete(membersBoard);
    }

    // 게시글 수정
    public void updateBoard(UpdateBoardDto updateBoardDto){
        Board existBoard = boardRepository.findById(updateBoardDto.getBoardSeq()).orElseThrow( // 해당 게시판 자체가 없는지 확인 및 영속처리
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_BOARD.getMsg()));
        UpdateBoardDto updatedBoard = membersBoardRepository.findMembersBoard(updateBoardDto.getMembersSeq(), updateBoardDto.getBoardSeq()); // DB에 있는값 이걸 업데이트를 쳐줘야함,
        if(updatedBoard == null){ // 본인 게시물이 아닌경우
            throw new NoSuchElementException(ErrorMessage.UNAUTHORIZED_PERMISSION.getMsg());
        }
        updatedBoard.setUpdateDto(updateBoardDto); // 수정값으로 업데이트처리
        existBoard.updateBoard(updatedBoard); // 기존 보드에 값을 업데이트 ,, 더티 체킹으로 자동 쿼리
    }

    // 게시판리스트 조회
    public Page<BoardListDto> getBoardList(BoardListDto boardListDto , Pageable pageable){
        Page<BoardListDto> boardList = boardRepository.findBoardListPaging(boardListDto, pageable);
        return boardList;
    }

    public Board getBoardDetail(long seq){
        Board board = boardRepository.findBoardWithCategory(seq);
        if(board == null){
            throw new NoSuchElementException(ErrorMessage.NOT_EXIST_BOARD.getMsg());
        }
        return board;
    }

}
