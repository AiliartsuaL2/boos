package hocheoltech.boos.mapper;

import hocheoltech.boos.domain.Members;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    int insertMember(Members members);
    int getMemberNum(String id);

    int findByIdPwd(Members members);
}
