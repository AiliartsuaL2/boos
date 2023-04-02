package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    int deleteBySeqAndMembersSeq(Long seq,Long membersSeq);
}
