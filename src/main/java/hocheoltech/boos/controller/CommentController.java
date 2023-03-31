package hocheoltech.boos.controller;

import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.BoardListDto;
import hocheoltech.boos.dto.CommentDto;
import hocheoltech.boos.jwt.JwtTokenProvider;
import hocheoltech.boos.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글", description = "댓글 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/v1/comment")
    @Operation(summary = "댓글작성 메서드", description = "댓글작성 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(schema = @Schema(implementation = Members.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = Members.class)))
    })
    public ResponseEntity<CommentDto> createBoard(@RequestBody CommentDto commentDto,
                                                  @RequestHeader(value = "Authorization") String jwtToken){
        // 헤더를 이용해서 membersSeq를 확인해야함
        String membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출

        commentDto.setWriterId(membersId);
        CommentDto comment = commentService.createComment(commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}