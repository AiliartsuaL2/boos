package hocheoltech.boos.service;


import hocheoltech.boos.domain.Members;
import hocheoltech.boos.exception.DuplicateMemberIdException;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

//    private final MemberMapper memberMapper;
    private final MembersRepository membersRepository;


    // 회원가입
    public Members saveMember(Members members){
        if(membersRepository.findById(members.getId())!=null){
            throw new DuplicateMemberIdException("use another id");
        }
        return membersRepository.save(members);
    }

    // 로그인
    public void loginMember(Members members) {
//        if(memberMapper.findByIdPwd(members) == 0){
//            throw new NoMatchedMemberInfoException("id and password do not match");
//        }
    }
}
