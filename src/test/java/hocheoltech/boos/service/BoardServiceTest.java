package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Category;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.BoardListDto;
import hocheoltech.boos.dto.CreateBoardDto;
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

        String writer = "luvsole3";
        long seq = 1L;

            for (int i = 1; i < 40; i++) {
                String nickname = memberService.findMember(writer).getNickname();
                CreateBoardDto board = CreateBoardDto.builder()
                        .title("테스트 제목" + i)
                        .content("테스트 내용" + i)
                        .writer(nickname)
                        .categoryName("테스트 게시판0")
                        .build();


                boardService.createBoard(board, writer);
            }
    }

    @Test
    void deleteBoard() {
        //given
        long boardSeq = 250L;
        String membersId = "luvsole4";

        //when
        boardService.deleteBoard(boardSeq,membersId);

        //then
//        Board boardDetail = boardService.getBoardDetail(boardSeq); // 존재하지 않는 게시판 출력,


    }


    @Test
    void getBoardListByMemberSeq() {
        PageRequest pageRequest = PageRequest.of(0,5);

//        Page<Board> boardList = boardService.getBoardList("ailiartsua", null, "5", null ,pageRequest);
//        for (Board board : boardList) {
//            System.out.println("board = " + board.getSeq());
//            System.out.println("category = "+board.getCategory().getCategoryName());
        }

    @Test
    void getBoardDetail(){
        //given
        long seq = 249L;
        //when
        Board boardDetail = boardService.getBoardDetail(seq);
//        String categoryName = boardDetail.getCategory().getCategoryName(); // DTO로 변환하던지 아니면 아래처럼 join해서 가져오던지 처리를 해야함.
        //then
        assertThat(boardDetail.getTitle()).isEqualTo("수정된 제목");
//        assertThat(categoryName).isEqualTo("비밀 게시판");
    }


    @Test
    @Transactional
    void updateBoard() {
        //given
        long boardSeq = 249L;

        Optional<Category> categoryOptional = categoryRepository.findById(3L);
        Category category = categoryOptional.get();

        Members noneOwner = memberService.findMember("ailiartsua");
        Members boardOwner = memberService.findMember("luvsole3");

        UpdateBoardDto updateBoardDto = UpdateBoardDto.builder()
                .boardCategory(category)
                .boardSeq(boardSeq)
                .boardTitle("수정된 제목")
                .boardContent("수정된 내용")
//                .membersSeq(boardOwner.getSeq())
                .membersId(noneOwner.getId()) // 권한관련 throw exception 발생
                .build();


        //when
        boardService.updateBoard(updateBoardDto); // 게시판 주인
        //then
//        assertThat(boardDetail.getContent()).isEqualTo("랍니다");
    }
}