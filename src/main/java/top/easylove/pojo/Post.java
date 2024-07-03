package top.easylove.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "post")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Post entity")
public class Post {

    @Transient
    public static final Long SERIAL_VERSION_UID = -6849794870754623710L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the post", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Column(name = "title", nullable = false, unique = true)
    @Schema(description = "Title of the post", example = "My First Post")
    private String title;

    @Column(name = "content", nullable = false)
    @Schema(description = "Content of the post", example = "This is the content of my first post.")
    private String content;

    @Column(name = "author_id", nullable = false)
    @CreatedBy
    @Schema(description = "ID of the author who created the post", example = "user123")
    private String author_id;

    @Column(name = "views")
    @Schema(description = "Number of views the post has received", example = "100")
    private String views;

    @Column(name = "created_at")
    @CreatedDate
    @Schema(description = "Date and time when the post was created", example = "2024-01-01T12:00:00Z")
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    @Schema(description = "Date and time when the post was last updated", example = "2024-01-02T12:00:00Z")
    private Date updatedAt;

    @Column(name = "status")
    @Schema(description = "Status of the post (0 for published, 1 for draft, 2 for archived)", example = "1")
    private Integer status;

    @Column(name = "likes")
    @Schema(description = "Number of likes the post has received", example = "50")
    private Integer likes;

    @Column(name = "reviews")
    @Schema(description = "Number of reviews the post has received", example = "10")
    private Integer reviews;

    @Column(name = "summary")
    @Schema(description = "Short summary of the post", example = "This is a brief summary of the post.")
    private String summary;

    @Column(name = "avatar")
    @Schema(description = "URL of the post's avatar image", example = "https://example.com/avatar.jpg")
    private String avatar;

    @Column(name = "feature")
    @Schema(description = "Feature status of the post (1 for top, 2 for recommended, 3 for others)", example = "1")
    private Integer feature;
}
