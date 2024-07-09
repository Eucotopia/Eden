package top.easylove.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "role")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Role entity representing user roles in the system")
public class Role implements Serializable {

    @Transient
    public static final Long SERIAL_VERSION_UID = -6849794870754623710L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the role", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    @Schema(description = "Name of the role", example = "ADMIN")
    private String name;

    @Column(name = "description")
    @Schema(description = "Description of the role", example = "Administrator role with full system access")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @Schema(description = "Timestamp when the role was created", example = "2023-04-15T10:30:00Z", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    @Schema(description = "Timestamp when the role was last updated", example = "2023-04-16T14:45:00Z", accessMode = Schema.AccessMode.READ_ONLY)
    private Date updatedAt;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @JsonIgnoreProperties(value = { "roles" })
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy = "roles")
    @Schema(description = "Set of users associated with this role", hidden = true)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @Schema(description = "Set of permissions associated with this role")
    private Set<Permission> permissions;
}