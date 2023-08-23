package niit.com.vn.demospring1.repository;

import niit.com.vn.demospring1.domains.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>, PagingAndSortingRepository<Category, Long> {
}
