package hocheoltech.boos.controller;

import hocheoltech.boos.dto.board.PageRequest;
import hocheoltech.boos.dto.message.DeleteMessageDto;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.jwt.handler.JwtTokenProvider;
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

    @PostMapping("/v1/message")
    public String saveMessage(@RequestBody MessageDto messageDto,
                              @RequestHeader(value = "Authorization") String jwtToken){
        String membersId = jwtTokenProvider.getUserPk(jwtToken);
        messageDto.setSenderId(membersId);

        messageService.sendMessage(messageDto);

        return "메세지가 성공적으로 전송되었습니다.";
    }

    // 쪽지함 다중 삭제의 경우 단건 삭제 url을 다중으로 호출하는것보다는 list로 받아서 서비스에서 for문으로 돌리는게 효율적일 것 같음,,
    @DeleteMapping("/v1/message")
    public String removeMessage(@RequestBody DeleteMessageDto deleteMessageDto,
                                @RequestHeader(value = "Authorization") String jwtToken){
        String membersId = jwtTokenProvider.getUserPk(jwtToken);

        deleteMessageDto.setMembersId(membersId);
        messageService.deleteMessage(deleteMessageDto);

        return "메세지가 성공적으로 삭제되었습니다.";
    }
    @GetMapping("/v1/message/sent")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "보낸 편지함 불러오기", description = "보낸 편지함을 불러오는 메서드입니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public Page<MessageDto> getSendedBoardList(PageRequest pageRequest,
                                         @RequestBody SearchMessageDto searchMessageDto,
                                         @RequestHeader(value = "Authorization") String jwtToken){

        Pageable pageable = pageRequest.of();
        String membersId = jwtTokenProvider.getUserPk(jwtToken);
        searchMessageDto.setSenderId(membersId);
        Page<MessageDto> messageList = messageService.getSendedMessageList(searchMessageDto, pageable);
        return messageList;
    }
    @GetMapping("/v1/message/receipt")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "받은 편지함 불러오기", description = "받은 편지함을 불러오는 메서드입니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public Page<MessageDto> getReceiptedBoardList(PageRequest pageRequest,
                                                      @RequestBody SearchMessageDto searchMessageDto,
                                                      @RequestHeader(value = "Authorization") String jwtToken){
        Pageable pageable = pageRequest.of();
        String membersId = jwtTokenProvider.getUserPk(jwtToken);
        searchMessageDto.setReceiptId(membersId);
        Page<MessageDto> messageList = messageService.getReceiptedMessageList(searchMessageDto, pageable);
        return messageList;
    }

}
