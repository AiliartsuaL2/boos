package hocheoltech.boos.mapper;
import hocheoltech.boos.domain.Members;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    int createMember(Members members);
    int getMemberNum(String id);

    int findByIdPwd(Members members);

    int updateMember(Members member);

    int deleteMember(String id);



}
