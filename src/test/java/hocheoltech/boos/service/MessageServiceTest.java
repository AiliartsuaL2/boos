package hocheoltech.boos.service;

import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.domain.Message;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.repository.MembersRepository;
import hocheoltech.boos.repository.MessageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

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
            String senderId = "luvsole"+i;
            String receiptId = "luvsole6";
            MessageDto messageDto = new MessageDto();
            messageDto.setSenderId(senderId);
            messageDto.setReceiptId(receiptId);
            messageDto.setContent("메세지 테스트 안녕하세요 이주홍비니다.");

            //when
            messageService.sendMessage(messageDto);

        }
        //then
//        Message message = messageRepository.findById(4L).get();
//        Assertions.assertThat(senderId).isEqualTo(message.getSenderId().getId());


    }

    @Test
    void deleteMessage() {
        //given
        MessageDto messageDto = new MessageDto();
        messageDto.setSenderId("cy123");
        messageDto.setMessageSeq("4");

        //when
        messageService.deleteMessage(messageDto);

        //then
        Message message = messageRepository.findById(4L).get();
        Assertions.assertThat(message.getDeleteYn()).isEqualTo(TFCode.TRUE);


    }
}