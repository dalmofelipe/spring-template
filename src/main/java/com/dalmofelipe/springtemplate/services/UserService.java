package com.dalmofelipe.springtemplate.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dalmofelipe.springtemplate.dtos.UserCreateDTO;
import com.dalmofelipe.springtemplate.dtos.UserOutputDTO;
import com.dalmofelipe.springtemplate.dtos.UserUpdateDTO;
import com.dalmofelipe.springtemplate.entities.UserModel;
import com.dalmofelipe.springtemplate.exceptions.business.EmailAlreadyInUseException;
import com.dalmofelipe.springtemplate.exceptions.business.UserNotFoundException;
import com.dalmofelipe.springtemplate.repositories.UserRepository;

@SuppressWarnings("null")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserOutputDTO> listAll() {
        return userRepository.findAllUsers();
    }

    public List<UserOutputDTO> searchWithFilters(String name, String role) {
        return userRepository.searchUserByFilters(name, role)
            .stream().map(u -> u.toDTO()).toList();
    }

    public UserModel save(UserCreateDTO userDto) {
        Optional<UserModel> opt = userRepository.findByEmail(userDto.getEmail());
        if (opt.isPresent())
            throw new EmailAlreadyInUseException();

        var user = userDto.toModel();
        
        return userRepository.save(user);
    }

    public UserOutputDTO showUser(UUID userId) {
        Optional<UserModel> opt = userRepository.findById(userId);
        
        return opt.map(UserModel::toDTO).orElseThrow(() -> new UserNotFoundException());
    }

    public void remove(UUID userId) {
        userRepository.deleteById(userId);
    }

    public UserModel update(UUID userId, UserUpdateDTO updateDto) {
        Optional<UserModel> opt = userRepository.findById(userId);
        if(opt.isEmpty())
            throw new UserNotFoundException();

        var userDb = opt.get();
        userDb.setName(updateDto.getName() != null ? updateDto.getName() : userDb.getName());
        userDb.setEmail(updateDto.getEmail() != null ? updateDto.getEmail() : userDb.getEmail());
        userDb.setRole(updateDto.getRole() != null ? updateDto.getRole() : userDb.getRole());
        
        return userRepository.save(userDb);
    }
}
