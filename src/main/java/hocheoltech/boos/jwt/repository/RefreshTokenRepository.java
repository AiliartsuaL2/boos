package hocheoltech.boos.jwt.repository;

import hocheoltech.boos.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    boolean existsByKeyId(String userEmail);
    void deleteByKeyId(String userEmail);
}
