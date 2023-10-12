package com.dalmofelipe.springtemplate.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dalmofelipe.springtemplate.dtos.UserOutputDTO;
import com.dalmofelipe.springtemplate.entities.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    // Mapeando consulta direto no DTO
    @Query("SELECT new com.dalmofelipe.springtemplate.dtos.UserOutputDTO(u.id, u.name, u.email, u.role) FROM UserModel u")
    List<UserOutputDTO> findAllUsers();

    Optional<UserModel> findByEmail(String email);
}
