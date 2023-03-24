package hocheoltech.boos.repository;

import hocheoltech.boos.domain.MembersBoard;
import hocheoltech.boos.dto.UpdateBoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MembersBoardRepository extends JpaRepository<MembersBoard, Long>, MembersBoardRepositoryCustom {
    UpdateBoardDto findMembersBoard(Long membersSeq, Long boardSeq);
    MembersBoard findMembersBoardByMembersSeqAndBoardSeq(Long membersSeq, Long boardSeq);
}
