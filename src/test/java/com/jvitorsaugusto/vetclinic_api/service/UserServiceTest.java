package com.jvitorsaugusto.vetclinic_api.service;

import com.jvitorsaugusto.vetclinic_api.dtos.authDtos.RegisterRequestDto;
import com.jvitorsaugusto.vetclinic_api.dtos.authDtos.RegisterResponseDto;
import com.jvitorsaugusto.vetclinic_api.exceptions.EmailAlreadyRegistered;
import com.jvitorsaugusto.vetclinic_api.model.PetModel;
import com.jvitorsaugusto.vetclinic_api.model.UserModel;
import com.jvitorsaugusto.vetclinic_api.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;


    @Test
    void registerNewUserSuccessfully() {
        RegisterRequestDto newUser = criarRegisterRequestDto();

        when(userRepository.findByEmail(newUser.email())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserModel.class)))
                .thenAnswer(i -> {
                    UserModel savedUser = i.getArgument(0);

                    savedUser.setId(UUID.randomUUID());
                    return savedUser;
                });

        String senhaCriptografada = "senhaCriptografada123XYZ";
        when(passwordEncoder.encode(newUser.password())).thenReturn(senhaCriptografada);

        RegisterResponseDto response = userService.registerUser(newUser);

        assertThat(newUser).isNotNull();
        assertThat(response).isNotNull();
        assertThat(response.email()).isEqualTo(newUser.email());
        assertThat(response.username()).isEqualTo(newUser.username());

        verify(userRepository, times(1)).findByEmail(newUser.email());
        verify(passwordEncoder, times(1)).encode(newUser.password());
        verify(userRepository, times(1)).save(any(UserModel.class));
    }


    @Test
    void attemptToRegisterUserWithEmailAlreadyRegistered() {
        RegisterRequestDto newUser = criarRegisterRequestDto();
        UserModel existingUser = criarUserModel();
        
        when(userRepository.findByEmail(newUser.email())).thenReturn(Optional.of(existingUser));

        assertThrows(EmailAlreadyRegistered.class, () -> {
            userService.registerUser(newUser);
        });

        verify(userRepository, never()).save(any(UserModel.class));
    }
    

    private RegisterRequestDto criarRegisterRequestDto() {
        return new RegisterRequestDto(
                "Utilizador de Teste",
                "teste@email.com",
                "senhaForte123",
                "11999998888"
        );
    }

    private UserModel criarUserModel() {
        return UserModel.builder()
                .id(UUID.randomUUID()) // Gera um UUID aleatório para o teste
                .userName("Utilizador de Teste")
                .email("teste@email.com")
                .password("senhaCriptografada123XYZ") // Simula uma senha que já teria sido encriptada
                .cellPhone("11999998888")
                .build();
    }
}