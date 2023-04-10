package hocheoltech.boos.repository.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.domain.QBlacklist;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.QMessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import hocheoltech.boos.repository.Custom.MessageRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hocheoltech.boos.domain.QBlacklist.blacklist;
import static hocheoltech.boos.domain.QMessage.message;
import static hocheoltech.boos.domain.QMembers.members;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MessageDto> findSendedMessageList(SearchMessageDto searchMessageDto , Pageable pageable) {

        List<MessageDto> messageList = queryFactory.select(new QMessageDto(
                        message.seq,
                        message.senderId.id,
                        message.recipientId.id,
                        message.senderId.nickname,
                        message.recipientId.nickname,
                        message.content,
                        message.sendTime))
                .from(message)
                .join(message.senderId, members)
                .join(members.blockList, blacklist)
                .where(message.senderId.id.eq(searchMessageDto.getSenderId()) // 본인이 보낸 쪽지만,
                        .and(message.recipientId.ne(blacklist.blockedId)) // 본인이 보낸 쪽지의 수신자가 차단한사람이면 나오지 않게
                        .and(messageContentContains(searchMessageDto.getContent()))
                        .and(messageRecipientIdContains(searchMessageDto.getReceiptId()))
                        .and(messageRecipientNicknameContains(searchMessageDto.getReceiptNickname()))
                        .and(message.deleteYn.eq(TFCode.FALSE)))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(messageSort(pageable))
                .fetch();

        Long count = queryFactory.select(message.count())
                .from(message)
                .join(message.senderId, members)
                .join(members.blockList, blacklist)
                .where(message.senderId.id.eq(searchMessageDto.getSenderId()) // 본인쪽지만,
                        .and(message.recipientId.ne(blacklist.blockedId))// 본인이 보낸 쪽지의 수신자가 차단한사람이면 나오지 않게
                        .and(messageContentContains(searchMessageDto.getContent()))
                        .and(messageRecipientIdContains(searchMessageDto.getReceiptId()))
                        .and(messageRecipientNicknameContains(searchMessageDto.getReceiptNickname()))
                        .and(message.deleteYn.eq(TFCode.FALSE))
                )
                .fetchOne();

        return new PageImpl<>(messageList, pageable, count);
    }

    @Override
    public Page<MessageDto> findReceiptedMessageList(SearchMessageDto searchMessageDto, Pageable pageable) {

        List<MessageDto> messageList = queryFactory.select(new QMessageDto(
                        message.seq,
                        message.senderId.id,
                        message.recipientId.id,
                        message.senderId.nickname,
                        message.recipientId.nickname,
                        message.content,
                        message.sendTime))
                .from(message)
                .join(message.recipientId, members)
                .join(members.blockList, blacklist)
                .where(message.recipientId.id.eq(searchMessageDto.getReceiptId()) // 본인쪽지만,
                        .and(message.senderId.ne(blacklist.blockedId))
                        .and(messageContentContains(searchMessageDto.getContent()))
                        .and(messageSenderIdContains(searchMessageDto.getSenderId()))
                        .and(messageSenderNicknameContains(searchMessageDto.getSenderNickname()))
                        .and(message.deleteYn.eq(TFCode.FALSE))
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(messageSort(pageable))
                .fetch();

        Long count = queryFactory.select(message.count())
                .from(message)
                .join(message.senderId, members)
                .join(members.blockList, blacklist)
                .where(message.recipientId.id.eq(searchMessageDto.getReceiptId()) // 본인쪽지만,
                        .and(message.senderId.ne(blacklist.blockedId))
                        .and(messageContentContains(searchMessageDto.getContent())) // 내용으로 검색
                        .and(messageSenderIdContains(searchMessageDto.getSenderId())) // 보낸사람 검색
                        .and(messageSenderNicknameContains(searchMessageDto.getSenderNickname()))
                        .and(message.deleteYn.eq(TFCode.FALSE))
                )
                .fetchOne();


//        List<MessageDto> collect = messageList.stream()
//                .map(m -> new MessageDto())
//                .collect(Collectors.toList()); // message entity를 dto로 변환

        return new PageImpl<>(messageList, pageable, count);
    }


    private BooleanExpression messageContentContains(String content) {
        return content != null ? message.content.contains(content) : null;
    }

    private BooleanExpression messageSenderIdContains(String sender) {
        return sender != null ? message.senderId.id.contains(sender) : null;
    }

    private BooleanExpression messageRecipientIdContains(String recipient) {
        return recipient != null ? message.recipientId.id.contains(recipient) : null;
    }

    private BooleanExpression messageRecipientNicknameContains(String nickname) {
        return nickname != null ? message.recipientId.nickname.contains(nickname) : null;
    }
    private BooleanExpression messageSenderNicknameContains(String nickname) {
        return nickname != null ? message.senderId.nickname.contains(nickname) : null;
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
