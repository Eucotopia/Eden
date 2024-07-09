package top.easylove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.easylove.pojo.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findCategoryByName(String name);
}
