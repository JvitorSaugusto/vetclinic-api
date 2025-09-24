package com.jvitorsaugusto.vetclinic_api.repositories;

import com.jvitorsaugusto.vetclinic_api.model.UserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;



@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should get user successfully from DB")
    void findUserByEmailSuccess() {
        String email = "teste@gmail.com";

        createUser();

        Optional<UserModel> result = userRepository.findByEmail(email);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get user from DB when user not exists")
    void findUserByEmailFail() {
        String email = "teste@gmail.com";
        Optional<UserModel> result = userRepository.findByEmail(email);

        assertThat(result.isEmpty()).isTrue();
    }

    private UserModel createUser(){

        UserModel newUser = UserModel.builder()
                .userName("teste")
                .email("teste@gmail.com")
                .cellPhone("12312321")
                .password("123")
                .build();

        return userRepository.save(newUser);
    }
}