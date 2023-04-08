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
        else if(blockerId.equals(blockedId)){ // blockedId는 필수값이니 equals 앞에 두고 npe 방지
            throw new IllegalArgumentException(ErrorMessage.DO_NOT_BLOCKED_SELF.getMsg());
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
        if(!membersRepository.existsById(membersId)){
            throw new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg());
        }
        List<BlacklistDto> blacklist = blackListRepository.findBlacklistByMembersId(membersId);
        if(blacklist.size() == 0){
            throw new NoSuchElementException(ErrorMessage.NOT_EXIST_BLACKLIST.getMsg());
        }
        return blacklist;
    }


}
