package hocheoltech.boos.controller;

import hocheoltech.boos.dto.blacklist.BlacklistDto;
import hocheoltech.boos.jwt.JwtTokenProvider;
import hocheoltech.boos.service.BlacklistService;
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

    @PostMapping("/v1/blacklist")
    public String createBlacklist(@RequestBody String idToBlock,
                               @RequestHeader(value = "Authorization") String jwtToken){
        String membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출
        blacklistService.createBlackList(membersId, idToBlock);

        return String.format("%s 회원이 정상적으로 차단되었습니다.", idToBlock);
    }

    @DeleteMapping("/v1/blacklist")
    public String deleteBlacklist(@RequestBody String idToBlock,
                                  @RequestHeader(value = "Authorization") String jwtToken){

        String membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출
        blacklistService.deleteBlackList(membersId, idToBlock);

        return String.format("%s 회원이 정상적으로 차단 해제 되었습니다.", idToBlock);
    }


    @GetMapping("/v1/blacklist")
    public ResponseEntity<List<BlacklistDto>> getBlackList(@RequestHeader(value = "Authorization") String jwtToken){
        String membersId = jwtTokenProvider.getUserPk(jwtToken); // 헤더 정보(jwt)로 membersId 추출
        List<BlacklistDto> membersBlacklist = blacklistService.getMembersBlacklist(membersId);
        return new ResponseEntity<>(membersBlacklist,HttpStatus.OK);
    }

}
