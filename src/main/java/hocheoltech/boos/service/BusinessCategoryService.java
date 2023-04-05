package hocheoltech.boos.service;

import hocheoltech.boos.domain.BusinessCategory;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.BusinessCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessCategoryService {

    private final BusinessCategoryRepository businessCategoryRepository;

    @Transactional
    public void createBusinessCategory(BusinessCategory businessCategory){
        if(businessCategoryRepository.existsBusinessCategoriesByCategoryName(businessCategory.getCategoryName())){
            throw new RejectedExecutionException(ErrorMessage.DUPLICATE_CATEGORY_NAME.getMsg());
        }
        businessCategoryRepository.save(businessCategory);
    }
}
