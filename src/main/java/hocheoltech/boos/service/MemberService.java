package hocheoltech.boos.service;


import hocheoltech.boos.domain.Members;
import hocheoltech.boos.exception.DuplicateMemberIdException;
import hocheoltech.boos.exception.NoMatchedMemberInfoException;
import hocheoltech.boos.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;


    // 회원가입
    public void saveMember(Members members){
        if(memberMapper.getMemberNum(members.getId()) == 1){ // 중복회원 있을경우
            throw new DuplicateMemberIdException("use another id");
        }
        memberMapper.createMember(members);
    }

    // 로그인
    public void loginMember(Members members) {
        if(memberMapper.findByIdPwd(members) == 0){
            throw new NoMatchedMemberInfoException("id and password do not match");
        }
    }
}
