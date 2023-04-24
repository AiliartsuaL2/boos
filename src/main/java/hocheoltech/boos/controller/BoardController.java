package hocheoltech.boos.controller;


import hocheoltech.boos.dto.board.*;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.jwt.JwtTokenProvider;
import hocheoltech.boos.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "게시판", description = "게시판 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;
    private final JwtTokenProvider jwtTokenProvider;


    /**
     *
     * @param createBoardDto : 화면에서 받아온 게시물 정보
     * @param jwtToken : 로그인시 인가된 jwt 헤더 정보, 추출해서 members Seq 확인에 사용
     * @return
     */
    @PostMapping("/v1/board")
    @Operation(summary = "게시글 작성 메서드", description = "게시글 작성 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(schema = @Schema(implementation = CreateBoardDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<CreateBoardDto> createBoard(@RequestBody CreateBoardDto createBoardDto,
                                                    @RequestHeader(value = "Authorization") String jwtToken){
        String membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출

        CreateBoardDto board = boardService.createBoard(createBoardDto, membersId);
        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }

    @PutMapping("/v1/board/{boardSeq}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시글 수정 메서드", description = "게시글 수정 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BoardDetailDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public String modifyBoardDetail(@PathVariable String boardSeq,
                                                         @RequestBody UpdateBoardDto updateBoardDto,
                                                         @RequestHeader(value = "Authorization", required = false) String jwtToken){
        String membersId = ""; // 검색조건 null 체크
        if(jwtToken != null){
            membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출
        }
        updateBoardDto.setBoardSeq(Long.parseLong(boardSeq));
        updateBoardDto.setMembersId(membersId);
        boardService.updateBoard(updateBoardDto);

        return "게시글이 성공적으로 수정되었습니다.";
    }


    @GetMapping("/v1/board/{boardSeq}")
    @Operation(summary = "게시글 상세 조회 메서드", description = "게시글 상세 조회 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BoardDetailDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<BoardDetailDto> getBoardDetail(@PathVariable String boardSeq,
                                          @RequestHeader(value = "Authorization", required = false) String jwtToken){
        String membersId = ""; // 검색조건 null 체크
        if(jwtToken != null){
            membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출
        }
        BoardDetailDto boardDetail = boardService.getBoardDetail(Long.parseLong(boardSeq),membersId);

        return new ResponseEntity<>(boardDetail, HttpStatus.OK);
    }


    /**
     *
     * @param pageRequest - 페이징 조건(page(페이지 쪽), size(한 번에 가져올 페이지 개수), direction(차순 ASC,DESC) , properties(정렬할 컬럼명)
     * @param boardListDto - 검색 조건 (writer(작성자), title(제목), content(내용), categoryName(카테고리명))
     * @return Json
     */
    @GetMapping("/v1/boardList")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시판 리스트 불러오기", description = "게시판 리스트 불러오는 메서드입니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BoardListDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public Page<BoardListDto> getBoardList(PageRequest pageRequest,
                                           @RequestBody BoardListDto boardListDto,
                                           @RequestHeader(value = "Authorization",required = false) String jwtToken){
        Pageable pageable = pageRequest.of();
        boardListDto.setNowMembersId(""); // 게시판 리스트 검색 조건 null 처리
        if(jwtToken != null){ // 사용자가 로그인 되어 있으면 차단한 사용자의 게시물을 숨기기 위해
            boardListDto.setNowMembersId(jwtTokenProvider.getUserPk(jwtToken));
        }
        Page<BoardListDto> boardList = boardService.getBoardList(boardListDto, pageable);
        return boardList;
    }

    @DeleteMapping("/v1/board")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시판 삭제", description = "게시판을 삭제하는 메서드입니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public String deleteBoard(@RequestHeader(value = "Authorization") String jwtToken, String boardSeq) {
        String membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출
        boardService.deleteBoard(Long.parseLong(boardSeq),membersId);
        return "게시판이 성공적으로 삭제되었습니다.";
    }
}
