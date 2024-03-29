package com.pvt.blog.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * @author eucotopia
 */
@Table(name = "user")
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
    @Transient
    private static final Long SERIAL_VERSION_UID = -6849794470754667710L;
    /**
     * 用户 ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    /**
     * 邮箱
     */
    @Column(name = "email", nullable = false)
    private String email;
    /**
     * 年龄
     */
    @Column(name = "age", nullable = false)
    private Integer age;
    /**
     * 密码
     */
    @Column(name = "password", nullable = false)
    private String password;
    /**
     * 座右铭
     */
    @Column(name = "motto", nullable = false)
    private String motto;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 头像地址
     */
    @Column(name = "avatar")
    private String avatar;
    /**
     * 账号状态
     */
    @Column(name = "state")
    private Integer state;

    @Transient
    private String status;
    /**
     * 地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 角色
     */
    @Transient
    private String role;
    /**
     * 角色
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Role.class)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
