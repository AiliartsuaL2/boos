package hocheoltech.boos.controller;

import hocheoltech.boos.dto.board.BoardListDto;
import hocheoltech.boos.dto.board.PageRequest;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.jwt.JwtTokenProvider;
import hocheoltech.boos.service.MessageService;
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
import org.springframework.web.bind.annotation.*;


@Tag(name = "쪽지", description = "쪽지 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MessageService messageService;

    @GetMapping("/v1/message")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "쪽지 보관함 불러오기", description = "쪽지 보관함을 불러오는 메서드입니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public Page<MessageDto> getBoardList(PageRequest pageRequest,
                                         @RequestBody SearchMessageDto searchMessageDto,
                                         @RequestHeader(value = "Authorization") String jwtToken){

        Pageable pageable = pageRequest.of();
        String membersId = jwtTokenProvider.getUserPk(jwtToken);
        searchMessageDto.setSenderId(membersId);
        Page<MessageDto> messageList = messageService.getMessageList(searchMessageDto, pageable);
        return messageList;
    }
}
