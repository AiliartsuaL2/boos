package hocheoltech.boos.service;

import hocheoltech.boos.domain.Category;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(Category category){
        if(categoryRepository.existsCategoryByCategoryName(category.getCategoryName())){
            throw new RejectedExecutionException(ErrorMessage.DUPLICATE_CATEGORY_NAME.getMsg());
        }
        return categoryRepository.save(category);
    }


}
