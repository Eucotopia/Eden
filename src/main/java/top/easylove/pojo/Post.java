package top.easylove.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private String title;

    @Column(name = "content",nullable = false)
    private String content;

}
