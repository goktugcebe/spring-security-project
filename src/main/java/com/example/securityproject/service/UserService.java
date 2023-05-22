package com.example.securityproject.service;

import com.example.securityproject.dto.UserDTO;
import com.example.securityproject.entities.User;
import com.example.securityproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean userExist(String email) {
        return findUserByEmail(email).isPresent();
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public User register(UserDTO userDTO) {
        //password encryption
        String password = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(password);

        User user = new User();

        //enable the new user
        user.setEnabled(true);

        //map userDTO to user
        modelMapper.map(userDTO, user);
        return save(user);
    }

}
