package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Comment;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.repository.Custom.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> , CommentRepositoryCustom {

    Optional<Comment> findCommentBySeqAndMembers(long seq, Members members);

}
