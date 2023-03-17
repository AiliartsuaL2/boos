package hocheoltech.boos.mapper;

import hocheoltech.boos.domain.Members;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    int insertMembers(Members members);
}
