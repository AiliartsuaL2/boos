package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Category;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.UpdateBoardDto;
import hocheoltech.boos.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Commit
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void createBoard() { // 등록일시 저장안됨, 확인 필요
        //given

        Long memberSeq = 1L;
        Long categorySeq = 1L;

        Optional<Category> categoryOptional = categoryRepository.findById(categorySeq);
        Category category = categoryOptional.get();

        for (int i = 0; i < 200; i++) {
            Board board = Board.builder()
                    .title("테스트 제목"+i)
                    .content("테스트 내용"+i)
                    .category(category)
                    .build();
            boardService.createBoard(board,memberSeq);

        }

    }

    @Test
    void deleteBoard() {
        //given
        long boardSeq = 132L;
        long membersSeq = 5L;

        //when
        boardService.deleteBoard(membersSeq,boardSeq);

        //then
//        Board boardDetail = boardService.getBoardDetail(boardSeq); // 존재하지 않는 게시판 출력,


    }


    @Test
    void getBoardListByMemberSeq() {
        PageRequest pageRequest = PageRequest.of(0,5);

        Page<Board> boardList = boardService.getBoardList(1L, null, "5", null ,pageRequest);
        for (Board board : boardList) {
            System.out.println("board = " + board.getSeq());
//            System.out.println("category = "+board.getCategory().getCategoryName());
        }
    }

    @Test
    void getBoardDetail(){
        //given
        long seq = 131;
        //when
        Board boardDetail = boardService.getBoardDetail(seq);
//        String categoryName = boardDetail.getCategory().getCategoryName(); // DTO로 변환하던지 아니면 아래처럼 join해서 가져오던지 처리를 해야함.
        //then
        assertThat(boardDetail.getTitle()).isEqualTo("수정된 데이터!!!");
//        assertThat(categoryName).isEqualTo("비밀 게시판");
    }


    @Test
    @Transactional
    void updateBoard() {
        //given
        long boardSeq = 131L;

        Optional<Category> categoryOptional = categoryRepository.findById(2L);
        Category category = categoryOptional.get();

        Members noneOwner = memberService.findMember(7L);
        Members boardOwner = memberService.findMember(5L);

        Board board = Board.builder()
                .title("수정된 데이터!!!")
                .content("랍니다!!!")
                .category(category)
                .build();
        UpdateBoardDto updateBoardDto = new UpdateBoardDto(board, boardOwner.getSeq());


        //when
        boardService.updateBoard(updateBoardDto.getMembersSeq(),boardSeq,updateBoardDto); // 게시판 주인
        //then
//        assertThat(boardDetail.getContent()).isEqualTo("랍니다");
    }
}