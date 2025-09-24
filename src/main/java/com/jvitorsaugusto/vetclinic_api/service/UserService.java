package com.jvitorsaugusto.vetclinic_api.service;

import com.jvitorsaugusto.vetclinic_api.dtos.authDtos.RegisterRequestDto;
import com.jvitorsaugusto.vetclinic_api.dtos.authDtos.RegisterResponseDto;
import com.jvitorsaugusto.vetclinic_api.exceptions.EmailAlreadyRegistered;
import com.jvitorsaugusto.vetclinic_api.model.UserModel;
import com.jvitorsaugusto.vetclinic_api.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponseDto registerUser(RegisterRequestDto newUser){

        if (userRepository.findByEmail(newUser.email()).isPresent()){
            throw new EmailAlreadyRegistered("\n" +
                    "email already registered, please try another one");
        }

        String authPassword = passwordEncoder.encode(newUser.password());
        UserModel userModel =  UserModel
                .builder()
                .userName(newUser.username())
                .email(newUser.email())
                .password(authPassword)
                .cellPhone(newUser.cellPhone())
                .build();

        UserModel savedUser = userRepository.save(userModel);
        return new RegisterResponseDto(savedUser);
    }
}
