package top.easylove.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@Schema(description = "Category entity representing a classification for posts")
@EntityListeners(AuditingEntityListener.class)
public class Category implements Serializable {

    @Transient
    private static final long SERIAL_VERSION_UID = -6849794478244667750L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the category", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Column(name = "name")
    @NotBlank(message = "Category name cannot be blank")
    @Size(max = 255, message = "Category name cannot exceed 255 characters")
    @Schema(description = "Name of the category", example = "Technology", required = true)
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Category description cannot be blank")
    @Size(max = 255, message = "Category description cannot exceed 255 characters")
    @Schema(description = "Description of the category", example = "All posts related to technology and innovation", required = true)
    private String description;

    @CreatedDate
    @Column(name = "created_at")
    @Schema(description = "Timestamp when the category was created", example = "2023-01-01T00:00:00Z", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Timestamp when the category was last updated", example = "2023-01-02T00:00:00Z", accessMode = Schema.AccessMode.READ_ONLY)
    private Date updatedAt;

    @Column(name = "parent_id")
    @Schema(description = "ID of the parent category, if this is a subcategory", example = "456e7890-e12b-34d5-a789-012345678901")
    private String parentId;

    @JsonIgnoreProperties(value = {"categories"})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Post.class, mappedBy = "categories")
    @Schema(description = "Set of posts associated with this category")
    private Set<Post> posts;
}