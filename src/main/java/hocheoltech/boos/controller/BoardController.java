package hocheoltech.boos.controller;


import hocheoltech.boos.domain.Board;
import hocheoltech.boos.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
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
    public ResponseEntity<Board> createBoard(@RequestBody Board board){
        Board createdBoard = boardService.createBoard(board,2L);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    @GetMapping("/v1/boardList")
    public String getBoardList(@RequestBody JSONObject reqData ){

        return "";
    }


}
