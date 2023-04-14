package hocheoltech.boos.service;

import hocheoltech.boos.domain.Members;
import hocheoltech.boos.domain.Message;
import hocheoltech.boos.dto.board.BoardListDto;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.BlacklistRepository;
import hocheoltech.boos.repository.MembersRepository;
import hocheoltech.boos.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;
    private final MembersRepository membersRepository;
    private final BlacklistRepository blacklistRepository;


    /**
     * 비즈니스 로직
     * 보낸 사람 : 차단한 사람에게는 메세지를 정상적으로 보냈다는 메세지와 함께 보낸 쪽지함에도 이력이 있어야함,
     * 받은 사람 : 차단한 사람에게는 메세지를 보낼 수 없다는 메세지를 보내고, 차단한 사람이 보낸 쪽지는 보관함에 보이지 않게 처리해야함,
     * >> 차단당한 사람이 차단한 사람에게 메세지를 보낼경우,
     *  - 메세지를 보내고,
     */
    @Transactional
    public void sendMessage(MessageDto messageDto){

        Members sender = membersRepository.findById(messageDto.getSenderId()).orElseThrow(() -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        Members receipt = membersRepository.findById(messageDto.getReceiptId()).orElseThrow(() -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));

        if(blacklistRepository.existsBlacklistByBlockerIdAndBlockedId(sender.getId(),receipt.getId())){ // 차단한 사용자이면 쪽지 보내기를 못하게 처리
            throw new RejectedExecutionException(ErrorMessage.REJECTED_MESSAGE_BY_BLOCK.getMsg());
        }

        Message.MessageBuilder tmpMsg = Message.builder()
                .sender(sender)
                .recipient(receipt)
                .content(messageDto.getContent());
        // 차단 당한사람이 차단한 사람에게 메세지를 보낸경우 False 설정
        tmpMsg.showInboxYn(blacklistRepository.existsBlacklistByBlockerIdAndBlockedId(messageDto.getReceiptId(),messageDto.getSenderId()) ? "N" : "Y");
        Message message = tmpMsg.build();

        messageRepository.save(message);
    }

    @Transactional
    public void deleteMessage(MessageDto messageDto){
        Message message = messageRepository.findById(Long.parseLong(messageDto.getMessageSeq())).orElseThrow(() -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MESSAGE.getMsg()));
        if(!message.getSenderId().getId().equals(messageDto.getSenderId())){
            throw new RejectedExecutionException(ErrorMessage.UNAUTHORIZED_PERMISSION.getMsg());
        }
        message.deleteMessage();
    }

    // 보낸 쪽지함
    public Page<MessageDto> getSendedMessageList(SearchMessageDto searchMessageDto , Pageable pageable){
        Page<MessageDto> messageList = messageRepository.findSendedMessageList(searchMessageDto, pageable);
        return messageList;
    }

    // 받은 쪽지함
    public Page<MessageDto> getReceiptedMessageList(SearchMessageDto searchMessageDto , Pageable pageable){
        Page<MessageDto> messageList = messageRepository.findReceiptedMessageList(searchMessageDto, pageable);
        return messageList;
    }


}
