package top.easylove.repository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.easylove.pojo.User;

import java.util.Optional;

@Repository
@Tag(name = "User Repository", description = "Repository for accessing user data")
public interface UserRepository extends JpaRepository<User, String> {

    @Operation(summary = "Check if user exists by email", description = "Checks if a user exists with the given email address")
    Boolean existsUserByEmail(String email);

    @Operation(summary = "Find user by email", description = "Finds a user with the given email address")
    Optional<User> findUserByEmail(String email);

}
