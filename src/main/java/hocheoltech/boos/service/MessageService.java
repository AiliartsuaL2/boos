package hocheoltech.boos.service;

import hocheoltech.boos.domain.Members;
import hocheoltech.boos.domain.Message;
import hocheoltech.boos.dto.board.BoardListDto;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import hocheoltech.boos.exception.ErrorMessage;
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

    @Transactional
    public void sendMessage(MessageDto messageDto){
        Members sender = membersRepository.findById(messageDto.getSenderId()).orElseThrow(() -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        Members receipt = membersRepository.findById(messageDto.getReceiptId()).orElseThrow(() -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        Message message = Message.builder()
                .sender(sender)
                .recipient(receipt)
                .content(messageDto.getContent())
                .build();
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

    public Page<MessageDto> getMessageList(SearchMessageDto searchMessageDto , Pageable pageable){
        Page<MessageDto> messageList = messageRepository.findMessageList(searchMessageDto, pageable);
        return messageList;
    }


}
