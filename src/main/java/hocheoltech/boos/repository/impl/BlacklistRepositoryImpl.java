package hocheoltech.boos.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hocheoltech.boos.domain.Blacklist;
import hocheoltech.boos.dto.blacklist.BlacklistDto;
import hocheoltech.boos.repository.Custom.BlacklistRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static hocheoltech.boos.domain.QBlacklist.blacklist;


@Repository
@RequiredArgsConstructor

public class BlacklistRepositoryImpl implements BlacklistRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public boolean existsBlacklistByBlockerIdAndBlockedId(String blockerId, String blockedId) {
        Long count = queryFactory.select(blacklist.count())
                .from(blacklist)
                .where(blacklist.blockerId.id.eq(blockerId)
                        .and(blacklist.blockedId.id.eq(blockedId)))
                .fetchOne();
        return count == 0 ? false : true;
    }

    @Override
    public void deleteByBlockerIdAndBlockedId(String blockerId, String blockedId) {
        queryFactory.delete(blacklist)
                .where(blacklist.blockerId.id.eq(blockerId)
                        .and(blacklist.blockedId.id.eq(blockedId)))
                .execute();

    }

    @Override
    public List<BlacklistDto> findBlacklistByMembersId(String membersId) {
        List<Blacklist> result = queryFactory.selectFrom(blacklist)
                .where(blacklist.blockerId.id.eq(membersId))
                .fetch();
        List<BlacklistDto> collect = result.stream()
                .map(r -> BlacklistDto.builder()
                        .membersId(membersId)
                        .blockedId(r.getBlockedId().getId())
                        .build()).collect(Collectors.toList());
        return collect;
    }
}
