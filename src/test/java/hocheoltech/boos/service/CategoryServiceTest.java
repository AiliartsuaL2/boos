package hocheoltech.boos.service;

import hocheoltech.boos.domain.Category;
import hocheoltech.boos.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Commit
    void createCategory() {
        for (int i = 0; i < 5; i++) {
            Category category1 = Category.builder()
                    .categoryName("테스트 게시판"+i)
                    .build();
            categoryService.createCategory(category1);

        }

    }
}