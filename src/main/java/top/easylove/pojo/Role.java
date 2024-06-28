package top.easylove.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Schema(description = "Role entity")
public class Role implements Serializable {

    @Transient
    public static final Long SERIAL_VERSION_UID = -6849794870754623710L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the role", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    @Schema(description = "Name of the role")
    private String name;

    @Column(name = "description")
    @Schema(description = "Description of the role")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;


    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> users;

}
