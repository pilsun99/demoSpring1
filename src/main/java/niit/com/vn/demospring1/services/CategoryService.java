package niit.com.vn.demospring1.services;

import niit.com.vn.demospring1.domains.Category;
import niit.com.vn.demospring1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public void saveCategory(String name){
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

}
