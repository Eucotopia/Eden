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

@Entity
@Table(name = "tag")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Tag entity representing a categorization label for posts")
public class Tag implements Serializable {

    @Transient
    private static final Long SERIAL_VERSION_UID = -6249794470714667710L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier of the tag", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    @Column(name = "color")
    @Schema(description = "Color code of the tag in hexadecimal format", example = "#FF5733")
    private String color;

    @Column(name = "icon")
    @Schema(description = "Icon identifier or URL for the tag", example = "fa-star")
    private String icon;

    @Column(name = "name")
    @Schema(description = "Name of the tag", example = "Technology")
    private String name;

    @Column(name = "description")
    @Schema(description = "Description of the tag", example = "Articles related to technology and innovation")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @Schema(description = "Timestamp when the tag was created", example = "2023-04-15T10:30:00Z", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    @Schema(description = "Timestamp when the tag was last updated", example = "2023-04-16T14:45:00Z", accessMode = Schema.AccessMode.READ_ONLY)
    private Date updatedAt;

    @Schema(description = "Set of posts associated with this tag")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Post.class, mappedBy = "tags")
    @JsonIgnoreProperties(value = { "tags" })
    private Set<Post> posts;
}