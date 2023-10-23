package com.dalmofelipe.springtemplate.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dalmofelipe.springtemplate.dtos.UserOutputDTO;
import com.dalmofelipe.springtemplate.entities.UserModel;
import com.dalmofelipe.springtemplate.enums.UserRole;


@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    // Mapeando consulta direto no DTO
    @Query("SELECT new com.dalmofelipe.springtemplate.dtos.UserOutputDTO(u.id, u.name, u.email, u.role) FROM UserModel u")
    List<UserOutputDTO> findAllUsers();


    Optional<UserModel> findByEmail(String email);

    
    @Query(
        value = "SELECT * FROM tb_users WHERE name LIKE CONCAT('%',:name,'%') AND role LIKE CONCAT('%',:role,'%')", 
        nativeQuery = true
    )
    List<UserModel> searchUserByFilters(@Param("name") String name, @Param("role") UserRole role);
}
