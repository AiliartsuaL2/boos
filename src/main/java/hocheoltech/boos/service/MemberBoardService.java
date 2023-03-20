package hocheoltech.boos.service;

import hocheoltech.boos.domain.MembersBoard;
import hocheoltech.boos.repository.MembersBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberBoardService {
    private final MembersBoardRepository memberBoardRepository;

    public void createMemberBoard(MembersBoard membersBoard){
        memberBoardRepository.save(membersBoard);
    }
}
