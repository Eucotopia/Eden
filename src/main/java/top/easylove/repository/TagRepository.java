package top.easylove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.easylove.pojo.Tag;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

    Boolean existsTagByName(String name);

}
