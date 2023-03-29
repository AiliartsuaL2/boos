package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsCategoryByCategoryName(String categoryName);

    Optional<Category> findByCategoryName(String categoryName);
}
