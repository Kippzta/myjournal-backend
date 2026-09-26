package org.myjournal.service;

import org.myjournal.dto.RegisterDTO;
import org.myjournal.entity.User;
import org.myjournal.repository.UserRepository;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped 
public class AuthService {

    @Inject 
    UserRepository userRepository;

    @Transactional 
    public User registerUser(RegisterDTO registerDTO) {
        User user = new User();

        user.setUsername(registerDTO.getUsername());

        user.setPassword(BcryptUtil.bcryptHash(registerDTO.getPassword()));

        user.setRole("user");

        userRepository.persist(user);

        return user;
    }

}
