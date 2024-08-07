package top.easylove.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author eucotopia
 */
@Table(name = "user")
@Entity
@Setter
@Getter
@Data
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "User entity")
public class User implements Serializable {

    @Transient
    private static final Long SERIAL_VERSION_UID = -6249794470754667710L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the user", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    @Schema(description = "Username of the user", example = "john_doe")
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @Schema(description = "Email of the user", example = "john.doe@example.com")
    private String email;

    @Column(name = "password", nullable = false)
    @Schema(description = "Encrypted password of the user", example = "password123")
    private String password;

    @Column(name = "phone_number")
    @Schema(description = "Phone number of the user", example = "+1234567890")
    private String phoneNumber;

    @Column(name = "avatar")
    @Schema(description = "URL of the user's avatar", example = "https://example.com/avatar.jpg")
    private String avatar = "https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1";

    @Column(name = "gender")
    @Schema(description = "Gender of the user", example = "0", allowableValues = {"0", "1", "2"})
    private Integer gender = 2;

    @Schema(description = "Status of the user", allowableValues = {"0", "1", "2", "3", "4"}, example = "0:Active;1:Inactive;2:Pending;3:Banned;4:Deleted")
    @Column(name = "status")
    private Integer status = 2;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    @Schema(description = "Creation date of the user account", example = "2023-01-01T12:00:00")
    private Date createAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Last update date of the user account", example = "2023-01-01T12:00:00")
    private Date update_at;

    @Column(name = "birth_date")
    @Schema(description = "Birth date of the user", example = "1990-01-01")
    private Date birthDate;

    @Column(name = "motto")
    @Schema(description = "Motto or personal slogan of the user", example = "Work hard, play hard.")
    private String motto = "Work hard, play hard.";

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            )
    )
    @JsonIgnoreProperties(value = { "users" })
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_permission",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

}
