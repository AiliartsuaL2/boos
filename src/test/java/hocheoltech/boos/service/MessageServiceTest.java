package hocheoltech.boos.service;

import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.domain.Message;
import hocheoltech.boos.dto.board.PageRequest;
import hocheoltech.boos.dto.message.DeleteMessageDto;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import hocheoltech.boos.repository.MembersRepository;
import hocheoltech.boos.repository.MessageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
@Transactional
class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Autowired
    MessageRepository messageRepository;
    @Test
    void sendMessage() {
        //given
        for (int i = 5; i < 20; i++) {
            String recptId = "luvsole20";
            MessageDto messageDto = new MessageDto();
            messageDto.setSenderId("luvsole"+i);
            messageDto.setReceiptId(recptId);messageDto.setContent("메세지 테스트 안녕하세요 이주홍비니다.");
            messageService.sendMessage(messageDto);

        }

//            String sendId = "luvsole13";
//            String recptId = "luvsole6";
//            MessageDto messageDto = new MessageDto();
//            messageDto.setSenderId(sendId);
//            messageDto.setReceiptId(recptId);messageDto.setContent("메세지 테스트 안녕하세요 이주홍비니다.");
//            messageService.sendMessage(messageDto);


//        String senderId = "luvsole10";
//        String receiptId = "luvsole6";
//        MessageDto messageDto = new MessageDto();
//        messageDto.setSenderId(senderId);
//        messageDto.setReceiptId(receiptId);messageDto.setContent("메세지 테스트 안녕하세요 이주홍비니다.");
            //when
//        messageService.sendMessage(messageDto);

        //then
        // 에러발생

//        Message message = messageRepository.findById(4L).get();
//        Assertions.assertThat(senderId).isEqualTo(message.getSenderId().getId());


    }

    @Test
    void deleteMessage() {
        //given
        List<Long> messageSeqList = new ArrayList<>();
        messageSeqList.add(385L);


        DeleteMessageDto deleteMessageDto = DeleteMessageDto.builder()
                .messageSeqList(messageSeqList)
                .membersId("luvsole26")
                .boxLocation("in")
                .build();

        //when
        messageService.deleteMessage(deleteMessageDto);

        //then
//        Message message = messageRepository.findById(4L).get();
//        Assertions.assertThat(message.getDeleteYn()).isEqualTo(TFCode.TRUE);


    }

    @Test
    void getSendedMessage(){
        //given
        PageRequest pageRequest = new PageRequest();
        pageRequest.setProperties("messageSeq");
        Pageable pageable = pageRequest.of();

        SearchMessageDto searchMessageDto = new SearchMessageDto();
        searchMessageDto.setSenderId("luvsole13");
        //when
        Page<MessageDto> sendedMessageList = messageService.getSendedMessageList(searchMessageDto, pageable);
        //then
        for (MessageDto messageDto : sendedMessageList) {
            System.out.println("messageDto.getContent() = " + messageDto.getContent());
        }

    }

    void getReceiptMessage(){
        //given
        PageRequest pageRequest = new PageRequest();
        pageRequest.setProperties("messageSeq");
        Pageable pageable = pageRequest.of();

        SearchMessageDto searchMessageDto = new SearchMessageDto();
        searchMessageDto.setSenderId("luvsole13");
        //when
        Page<MessageDto> sendedMessageList = messageService.getSendedMessageList(searchMessageDto, pageable);
        //then
        for (MessageDto messageDto : sendedMessageList) {
            System.out.println("messageDto.getContent() = " + messageDto.getContent());
        }

    }
}