package com.dalmofelipe.springtemplate.services;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.dalmofelipe.springtemplate.dtos.UserDto;
import com.dalmofelipe.springtemplate.entities.UserModel;
import com.dalmofelipe.springtemplate.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> listAll() {
        return userRepository.findAllUsers();
    }

    public UserModel save(UserDto userDto) {
        Optional<UserModel> opt = userRepository.findByEmail(userDto.getEmail());
        if (opt.isPresent())
            return null; // TODO implementar exception

        var user = new UserModel();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return userRepository.save(user);
    }

    public UserDto showUser(UUID userId) {
        Optional<UserModel> opt = userRepository.findById(userId);
        // TOOD implementar exception
        return opt.map(UserModel::toDto).orElse(null);
    }

    public void remove(UUID userId) {
        userRepository.deleteById(userId);
    }

    public UserModel update(UUID userId, UserDto userDto) {
        Optional<UserModel> opt = userRepository.findById(userId);
        if(opt.isEmpty())
            return null; // TODO implementar exception
        var user = opt.get();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }
}
