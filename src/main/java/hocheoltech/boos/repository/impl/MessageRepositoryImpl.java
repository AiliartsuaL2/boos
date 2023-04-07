package hocheoltech.boos.repository.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.domain.Message;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import hocheoltech.boos.repository.Custom.MessageRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static hocheoltech.boos.domain.QBoard.board;
import static hocheoltech.boos.domain.QMembersBoard.membersBoard;
import static hocheoltech.boos.domain.QMessage.message;
import static hocheoltech.boos.domain.QMembers.members;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    //Todo N+1 생각해보기
    @Override
    public Page<MessageDto> findMessageList(SearchMessageDto searchMessageDto , Pageable pageable) {
        List<Message> messageList = queryFactory.selectFrom(message)
                .join(message.senderId, members)
                .where(message.senderId.id.eq(searchMessageDto.getSenderId()) // 본인쪽지만,
                        .and(messageContentContains(searchMessageDto.getContent()))
                        .and(messageRecipientIdContains(searchMessageDto.getReceiptId()))
                        .and(messageRecipientNicknameContains(searchMessageDto.getReceiptNickname()))
                        .and(message.deleteYn.eq(TFCode.FALSE))
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(messageSort(pageable))
                .fetch();

        Long count = queryFactory.select(message.count())
                .from(message)
                .join(message.senderId, members)
                .where(messageContentContains(searchMessageDto.getContent())
                        .and(messageRecipientIdContains(searchMessageDto.getReceiptId()))
                        .and(messageRecipientNicknameContains(searchMessageDto.getReceiptNickname()))
                        .and(message.deleteYn.eq(TFCode.FALSE))
                )
                .fetchOne();

        List<MessageDto> collect = messageList.stream()
                .map(MessageDto::new)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, count);
    }


    private BooleanExpression messageContentContains(String content) {
        return content != null ? message.content.contains(content) : null;
    }

    private BooleanExpression messageRecipientIdContains(String recipient) {
        return recipient != null ? message.recipientId.id.contains(recipient) : null;
    }

    private BooleanExpression messageRecipientNicknameContains(String nickname) {
        return nickname != null ? message.recipientId.nickname.contains(nickname) : null;
    }


    private OrderSpecifier<?> messageSort(Pageable pageable) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!pageable.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : pageable.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                // 서비스에서 넣어준 정렬 조건을 스위치 케이스 문을 활용하여 셋팅하여 준다.
                switch (order.getProperty()){
                    case "messageSeq":
                        return new OrderSpecifier(direction, message.seq);
                    case "sendTime":
                        return new OrderSpecifier(direction, message.sendTime);
                }
            }
        }
        return null;
    }

}
