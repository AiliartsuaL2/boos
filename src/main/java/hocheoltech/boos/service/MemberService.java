package hocheoltech.boos.service;


import hocheoltech.boos.domain.Members;
import hocheoltech.boos.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void saveMember(Members members){
        memberMapper.insertMembers(members);
    }


}
