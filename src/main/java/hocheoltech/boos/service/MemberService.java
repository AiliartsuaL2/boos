package hocheoltech.boos.service;


import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.exception.DuplicateMemberIdException;
import hocheoltech.boos.exception.IncorrectLoginInfoException;
import hocheoltech.boos.repository.BoardRepository;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

//    private final MemberMapper memberMapper;
    private final MembersRepository membersRepository;
    private final BoardRepository boardRepository;


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

    public Members findMember(Long id){
        Optional<Members> members1 = membersRepository.findById(id);
        Members result = null;
        if(members1.isPresent()){
            result = members1.get();
        }
        return result;
    }

    public void deleteMember(Long id){
        if(!membersRepository.existsById(id)){
            throw new DuplicateMemberIdException("존재하는 id가 없습니다.");
        }
        membersRepository.deleteById(id);
    }

}
