package hocheoltech.boos.service;

import hocheoltech.boos.domain.Category;
import hocheoltech.boos.exception.DuplicateCategoryNameException;
import hocheoltech.boos.exception.DuplicateMemberIdException;
import hocheoltech.boos.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        if(categoryRepository.existsCategoryByCategoryName(category.getCategoryName())){
            throw new DuplicateCategoryNameException("use another category name");
        }
        return categoryRepository.save(category);
    }


}
