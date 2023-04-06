package hocheoltech.boos.repository;

import hocheoltech.boos.dto.blacklist.BlacklistDto;

import java.util.List;

public interface BlacklistRepositoryCustom {

    boolean existsBlacklistByBlockerIdAndBlockedId(String blockerId, String blockedId);
    void deleteByBlockerIdAndBlockedId(String blockerId, String blockedId);

    List<BlacklistDto> findBlacklistByMembersId(String membersId);
}
