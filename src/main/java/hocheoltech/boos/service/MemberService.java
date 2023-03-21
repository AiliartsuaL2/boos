package hocheoltech.boos.service;


import hocheoltech.boos.domain.Members;
import hocheoltech.boos.exception.DuplicateMemberIdException;
import hocheoltech.boos.exception.IncorrectLoginInfoException;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

//    private final MemberMapper memberMapper;
    private final MembersRepository membersRepository;


    // 회원가입
    public Members saveMember(Members members){
        if(membersRepository.existsById(members.getId())){
            throw new DuplicateMemberIdException("use another id");
        }

        return membersRepository.save(members);
    }

    // 로그인
    public void loginMember(Members members) {
        if(!membersRepository.existsByIdAndPassword(members.getId(), members.getPassword())){
            throw new IncorrectLoginInfoException("incorrect login info");
        }
        log.info("로그인 성공 로그인 id = {}",members.getId());
    }
}
