package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

//    @Test
//    void createBoard() {
//        //given
//            Board board = new Board();
//            board.setCategory("JPA 카테고리");
//            board.setTitle("JPA 제목");
//            board.setContent("JPA 내용");
//            board.setRegId("새로운 ID");
//        //when
//        boardService.createBoard(board);
//
//
//        //then
////        Optional<Board> boardDetail = boardService.getBoardDetail(12);
////        assertThat(boardDetail.getRegId()).isEqualTo(board.getRegId());
//    }

    @Test
    void deleteBoard() {
        //given
        long seq = 4;

        //when
        boardService.deleteBoard(seq);

        //then
        Optional<Board> boardDetail = boardService.getBoardDetail(seq);

        Assert.isNull(boardDetail);

    }

//    @Test
//    void updateBoard() {
//        //given
//        long seq = 7;
//        Board board = new Board();
//        board.setSeq(seq);
//        board.setCategory("카테고리 수정");
//        board.setTitle("제목수정");
//        board.setContent("내용수정");
//        //when
//        boardService.updateBoard(board);
//        //then
////        Board boardDetail = boardService.getBoardDetail(seq);
//
////        assertThat(boardDetail.getTitle()).isEqualTo(board.getTitle());
//
//    }

    @Test
    void getBoardList() {
    }

    @Test
    void getBoardDetail() {
        //given
        long seq = 5;
        //when
        Optional<Board> boardDetail = boardService.getBoardDetail(seq);
        //then
//        assertThat(boardDetail.getSeq()).isEqualTo(5);
    }
}