package hocheoltech.boos.service;

import hocheoltech.boos.domain.MemberBoard;
import hocheoltech.boos.repository.MemberBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberBoardService {
    private final MemberBoardRepository memberBoardRepository;

    public void createMemberBoard(MemberBoard memberBoard){
        memberBoardRepository.save(memberBoard);
    }
}
