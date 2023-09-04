package com.dalmofelipe.springtemplate.entities;

import com.dalmofelipe.springtemplate.dtos.UserDto;
import com.dalmofelipe.springtemplate.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "tb_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private UserRole role;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    {
        this.createdAt = LocalDateTime.now();
    }

    public UserDto toDto() {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
