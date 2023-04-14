package hocheoltech.boos.service;

import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.blacklist.BlacklistDto;
import hocheoltech.boos.repository.BlacklistRepository;
import hocheoltech.boos.repository.MembersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
@Transactional
class BlacklistServiceTest {
    @Autowired
    BlacklistService blacklistService;

    @Autowired
    MembersRepository membersRepository;

    @Autowired
    BlacklistRepository blacklistRepository;


    @Test
    void createBlackList() {
        //given
        String blockedId = "luvsole5";
        String blockerId = "luvsole13";
        //when
        blacklistService.createBlackList(blockerId,blockedId);
        //then
//        List<BlacklistDto> blacklistDtos = blacklistService.getMembersBlacklist(blockerId);
//
////        boolean result = false;
////        for (BlacklistDto blacklistDto : blacklistDtos) {
////            if(blockerId.equals(blacklistDto.getMembersId()) && blockedId.equals(blacklistDto.getBlockedId())){
////                result = true;
////                break;
////            }
////        }
////        assertTrue(result);

    }

    @Test
    void deleteBlackList() {
    }
}