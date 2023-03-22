package hocheoltech.boos.service;

import hocheoltech.boos.domain.Category;
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

    @Test
    @Commit
    void createCategory() {
        Category category = Category.builder()
                .categoryName("비밀 게시판")
                .build();

        categoryService.createCategory(category);

    }
}