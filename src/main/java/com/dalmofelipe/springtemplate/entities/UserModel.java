package com.dalmofelipe.springtemplate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.dalmofelipe.springtemplate.dtos.UserOutputDTO;
import com.dalmofelipe.springtemplate.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    // @ColumnTransformer(write = "?::user_role", read = "?::user_role") // postgres
    private UserRole role;

    @JsonProperty("created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    {
        this.createdAt = LocalDateTime.now();
    }

    public UserOutputDTO toDTO() {
        UserOutputDTO dto = new UserOutputDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
