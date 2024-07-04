package top.easylove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.easylove.pojo.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
}
