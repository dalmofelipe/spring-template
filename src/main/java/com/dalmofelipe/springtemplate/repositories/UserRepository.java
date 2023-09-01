package com.dalmofelipe.springtemplate.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dalmofelipe.springtemplate.dtos.UserDto;
import com.dalmofelipe.springtemplate.entities.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    // Mapeado consulta direto no DTO
    @Query("SELECT new com.dalmofelipe.springtemplate.dtos.UserDto(u.id, u.name, u.email) FROM UserModel u")
    List<UserDto> findAllUsers();

    Optional<UserModel> findByEmail(String email);
}
