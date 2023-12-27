package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
