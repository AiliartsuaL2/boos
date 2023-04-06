package hocheoltech.boos.service;

import hocheoltech.boos.domain.Blacklist;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.blacklist.BlacklistDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.BlacklistRepository;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlacklistService {

    private final BlacklistRepository blackListRepository;
    private final MembersRepository membersRepository;

    @Transactional
    public void createBlackList(String blockerId, String blockedId){
        Members blocker = membersRepository.findById(blockerId).orElseThrow(() -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        Members blockedMembers = membersRepository.findById(blockedId).orElseThrow(() -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));

        if(blackListRepository.existsBlacklistByBlockerIdAndBlockedId(blockerId,blockedId)){
            throw new IllegalArgumentException(ErrorMessage.ALREADY_BLOCKED_MEMBERS.getMsg());
        }

        Blacklist blacklist = Blacklist.builder()
                .blockerId(blocker)
                .blockedId(blockedMembers)
                .build();
        blackListRepository.save(blacklist);
    }

    @Transactional
    public void deleteBlackList(String blockerId, String blockedId) {
        if(!blackListRepository.existsBlacklistByBlockerIdAndBlockedId(blockerId,blockedId)){
            throw new IllegalArgumentException(ErrorMessage.NOT_BLOCKED_MEMBERS.getMsg());
        }
        blackListRepository.deleteByBlockerIdAndBlockedId(blockerId, blockedId);
    }

    public List<BlacklistDto> getMembersBlacklist(String membersId){
        List<BlacklistDto> blacklist = blackListRepository.findBlacklistByMembersId(membersId); // 사용자는 jwt로 인증하기 때문에 에러처리 없이 진행
        if(blacklist.size() == 0){
            throw new NoSuchElementException(ErrorMessage.NOT_EXIST_BLACKLIST.getMsg());
        }
        return blacklist;
    }


}
