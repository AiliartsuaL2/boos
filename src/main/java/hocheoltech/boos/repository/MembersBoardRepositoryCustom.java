package hocheoltech.boos.repository;

import hocheoltech.boos.dto.UpdateBoardDto;

public interface MembersBoardRepositoryCustom {
    UpdateBoardDto findMembersBoard(Long membersSeq, Long boardSeq);
}