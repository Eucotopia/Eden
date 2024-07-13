package top.easylove.repository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.easylove.pojo.Role;

import java.util.Optional;
import java.util.Set;

@Repository
@Tag(name = "Role Repository", description = "Repository for accessing role data")
public interface RoleRepository extends JpaRepository<Role, String> {

    @Operation(summary = "Find role by name", description = "Find role by name")
    Optional<Role> findRoleByName(String name);


    Optional<Set<Role>> findRolesByName(String name);
}
