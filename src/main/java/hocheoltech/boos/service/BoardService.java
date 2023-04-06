package hocheoltech.boos.service;

import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Category;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.domain.MembersBoard;
import hocheoltech.boos.dto.*;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

//    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;
    private final MembersRepository membersRepository;
    private final MembersBoardRepository membersBoardRepository;
    private final CategoryRepository categoryRepository;


    // 게시글 등록
    @Transactional
    public CreateBoardDto createBoard(CreateBoardDto createBoardDto, String membersId){
        Members members = membersRepository.findById(membersId).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        String categoryName = createBoardDto.getCategoryName();
        Category category = categoryRepository.findByCategoryName(categoryName).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.INCORRECT_CATEGORYNAME.getMsg()));

        Board board = Board.builder()
                .category(category)
                .writer(members.getNickname())
                .title(createBoardDto.getTitle())
                .content(createBoardDto.getContent())
                .build();

        membersBoardRepository.save(MembersBoard.builder().members(members).board(board).build()); // Members_board를 저장하며 board cascade로 저장처리

        CreateBoardDto resultDto = CreateBoardDto.builder()
                .seq(board.getSeq())
                .writer(board.getWriter())
                .title(board.getTitle())
                .categoryName(board.getCategory().getCategoryName())
                .content(board.getContent())
                .regTime(board.getRegTime())
                .build();

        return resultDto;
    }

    @Transactional
    public void deleteBoard(long boardSeq, String membersId){
        Board board = boardRepository.findBoardBySeqAndWriter(boardSeq, membersId).orElseThrow( // 해당 게시판 자체가 없는지 확인 및 영속처리
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_BOARD.getMsg()));
        board.deleteBoard(); // 영속성 프레임워크에 의해 자동 update쿼리
    }

    // 게시글 수정
    @Transactional
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

    public BoardDetailDto getBoardDetail(long seq){

        Board board = boardRepository.findBoardWithCategory(seq).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_BOARD.getMsg()));

        BoardDetailDto boardDetailDto = BoardDetailDto.builder()
                .seq(board.getSeq())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .regTime(board.getRegTime())
                .modifyTime(board.getModifyTime())
                .categorySeq(String.valueOf(board.getCategory().getSeq()))
                .modifyYn((board.getModifyYn().equals(TFCode.TRUE)) ? "Y" : "N")
                .deleteYn((board.getDeleteYn().equals(TFCode.TRUE)) ? "Y" : "N")
                .commentList(board.getComments().stream().map(c -> CommentDto.builder()
                        .boardSeq(String.valueOf(c.getBoard().getSeq()))
                        .seq(String.valueOf(c.getSeq()))
                        .writerId(c.getMembers().getId())
                        .anonymousYn((c.getAnonymousYn().equals(TFCode.TRUE))?"Y":"N")
                        .regTime(c.getRegTime())
                        .content(c.getContent())
                        .build()).collect(Collectors.toList()))
                .build();

        return boardDetailDto;
    }

}
