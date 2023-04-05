package hocheoltech.boos.service;

import hocheoltech.boos.domain.BusinessCategory;
import hocheoltech.boos.repository.BusinessCategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
@Transactional
class BusinessCategoryServiceTest {

    @Autowired
    BusinessCategoryService businessCategoryService;

    @Autowired
    BusinessCategoryRepository businessCategoryRepository;

    @Test
    void createBusinessCategory() {
        //given
        BusinessCategory category = BusinessCategory.builder()
                .categoryName("쇼핑몰")
                .build();
        //when
        businessCategoryService.createBusinessCategory(category);
        //then
        Assertions.assertThat(businessCategoryRepository.existsBusinessCategoriesByCategoryName("쇼핑몰")).isTrue();

    }
}