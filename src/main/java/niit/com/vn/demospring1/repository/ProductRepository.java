package niit.com.vn.demospring1.repository;

import niit.com.vn.demospring1.domains.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
