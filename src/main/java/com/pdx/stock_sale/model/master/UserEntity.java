package com.pdx.stock_sale.model.master;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mas_user")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt ;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name = "fname")
    private String fName;

    @Column(name="lname")
    private String lName;

    @Column(name = "position")
    private String position;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name = "telegram_id")
    private String telegramId;

    @Column(name="address")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "mas_user_role",
            joinColumns = {
            @JoinColumn(name = "user_id",referencedColumnName = "id")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "role_id",referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private Set<RoleEntity> roles;
}
