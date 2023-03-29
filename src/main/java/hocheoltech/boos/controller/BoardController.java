package hocheoltech.boos.controller;


import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.BoardListDto;
import hocheoltech.boos.dto.PageRequest;
import hocheoltech.boos.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/v1/board")
    @Operation(summary = "게시글 작성 메서드", description = "게시글 작성 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(schema = @Schema(implementation = Board.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = Board.class)))
    })
    public ResponseEntity<Board> createBoard(@RequestBody Board board, @RequestBody Members members){
        Board createdBoard = boardService.createBoard(board, members.getSeq());
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    @GetMapping("/v1/boardList")
    public Page<BoardListDto> getBoardList(PageRequest pageRequest,
                                           @RequestBody BoardListDto boardListDto){
        //Long membersSeq, Long categorySeq, String boardTitle, String boardContent , Pageable pageable
        // reqData : 검색필터(작성자,카테고리,제목,내용)
        String writer = boardListDto.getWriter();
        String title = boardListDto.getTitle();
        String content = boardListDto.getContent();
        String categoryName = boardListDto.getCategoryName();

        Pageable pageable = pageRequest.of();

        Sort sort = pageable.getSort();
        for (Sort.Order order : sort) {
            System.out.println("property = " + order.getProperty());
            System.out.println("direction = " + order.getDirection());
        }

        Page<BoardListDto> boardList = boardService.getBoardList(writer, categoryName, title, content, pageable);
        return boardList;
    }
}
