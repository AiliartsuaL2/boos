package hocheoltech.boos.service;

import hocheoltech.boos.domain.Category;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        if(categoryRepository.existsCategoryByCategoryName(category.getCategoryName())){
            throw new RejectedExecutionException(ErrorMessage.DUPLICATE_CATEGORY_NAME.getMsg());
        }
        return categoryRepository.save(category);
    }


}
