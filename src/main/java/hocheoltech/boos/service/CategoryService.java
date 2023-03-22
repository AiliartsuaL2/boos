package hocheoltech.boos.service;

import hocheoltech.boos.domain.Category;
import hocheoltech.boos.exception.DuplicateIdException;
import hocheoltech.boos.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        if(categoryRepository.existsCategoryByCategoryName(category.getCategoryName())){
            throw new DuplicateIdException("중복된 카테고리 이름입니다.");
        }
        return categoryRepository.save(category);
    }


}
