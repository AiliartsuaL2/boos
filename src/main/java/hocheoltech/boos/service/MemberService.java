package hocheoltech.boos.service;


import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.MembersBoardRepository;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

//    private final MemberMapper memberMapper;
    private final MembersRepository membersRepository;
    private final MembersBoardRepository membersBoardRepository;


    // 회원가입
    public Members saveMember(Members members){
        if(membersRepository.existsById(members.getId())){
            throw new RejectedExecutionException(ErrorMessage.DUPLICATE_MEMBER_ID.getMsg());
        }
        return membersRepository.save(members);
    }

    // 로그인
    public void loginMember(Members members) {
        if(!membersRepository.existsByIdAndPassword(members.getId(), members.getPassword())){
            throw new NoSuchElementException(ErrorMessage.INCORRECT_MEMBER_INFO.getMsg());
        }
        log.info("로그인 성공 로그인 id = {}",members.getId());
    }

    public Members findMember(Long id){
        Members members = membersRepository.findById(id).orElseThrow( // 해당 사용자가 없으면 Throw
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        return members;
    }

    public void deleteMember(Long id){
        if(!membersRepository.existsById(id)){
            throw new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg());
        }
        membersRepository.deleteById(id);
    }

}
