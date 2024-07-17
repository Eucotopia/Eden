package top.easylove.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "permission")
@Data
@Schema(description = "Permission entity")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the permission", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Column(name = "name")
    @Schema(description = "Name of the permission", example = "READ_PRIVILEGE")
    private String name;

    @Column(name = "description")
    @Schema(description = "Description of the permission", example = "Allows read access to resources")
    private String description;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    @Schema(description = "Creation date of the permission", example = "2023-01-01T12:00:00")
    private Date createAt;

}
