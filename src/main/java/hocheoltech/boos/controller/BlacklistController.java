package hocheoltech.boos.controller;

import hocheoltech.boos.dto.blacklist.BlacklistDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.jwt.handler.JwtTokenProvider;
import hocheoltech.boos.service.BlacklistService;
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

import java.util.List;

@Tag(name = "차단", description = "차단 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class BlacklistController {
    private final BlacklistService blacklistService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "사용자 차단", description = "사용자를 차단하는 불러오는 메서드입니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping("/v1/blacklist")
    @ResponseStatus(HttpStatus.CREATED)
    public String createBlacklist(@RequestBody String idToBlock,
                               @RequestHeader(value = "Authorization") String jwtToken){
        String membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출
        blacklistService.createBlackList(membersId, idToBlock);

        return String.format("%s 회원이 정상적으로 차단되었습니다.", idToBlock);
    }

    @Operation(summary = "사용자 차단 해제", description = "차단된 사용자를 해제하는 메서드입니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @DeleteMapping("/v1/blacklist")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBlacklist(@RequestBody String idToBlock,
                                  @RequestHeader(value = "Authorization") String jwtToken){

        String membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출
        blacklistService.deleteBlackList(membersId, idToBlock);

        return String.format("%s 회원이 정상적으로 차단 해제 되었습니다.", idToBlock);
    }

    @Operation(summary = "차단 목록 확인", description = "차단 목록을 확인하는 메서드입니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/v1/blacklist")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BlacklistDto>> getBlackList(@RequestHeader(value = "Authorization") String jwtToken){
        String membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출
        List<BlacklistDto> membersBlacklist = blacklistService.getMembersBlacklist(membersId);
        return new ResponseEntity<>(membersBlacklist,HttpStatus.OK);
    }

}
