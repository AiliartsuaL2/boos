package hocheoltech.boos.repository;

import hocheoltech.boos.domain.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory,Long> {
    Optional<BusinessCategory> findByCategoryName(String categoryName);
    boolean existsBusinessCategoriesByCategoryName(String categoryName);
}
