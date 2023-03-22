package hocheoltech.boos.service;


import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.exception.DuplicateIdException;
import hocheoltech.boos.repository.MembersBoardRepository;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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
            throw new DuplicateIdException("중복된 아이디 입니다.");
        }
        return membersRepository.save(members);
    }

    // 로그인
    public void loginMember(Members members) {
        if(!membersRepository.existsByIdAndPassword(members.getId(), members.getPassword())){
            throw new IllegalArgumentException("잘못된 로그인 정보입니다.");
        }
        log.info("로그인 성공 로그인 id = {}",members.getId());
    }

    public Members findMember(Long id){
        Members members = membersRepository.findById(id).orElseThrow( // 해당 사용자가 없으면 Throw
                () -> new NoSuchElementException("일치하는 사용자가 없습니다 seq : " + id));
        return members;
    }

    public void deleteMember(Long id){
        if(!membersRepository.existsById(id)){
            throw new NoSuchElementException("존재하는 id가 없습니다.");
        }
        membersRepository.deleteById(id);
    }

}
