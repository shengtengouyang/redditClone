package com.shen.redditclone.services;

import com.shen.redditclone.domain.User;
import com.shen.redditclone.repositery.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        encoder=new BCryptPasswordEncoder();
        this.roleService = roleService;
    }

    public User register(User user) {
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setEnabled(false);
        user.setPassword(secret);
        user.setConfirmPassword(secret);
        user.addRole(roleService.findByName("ROLE_USER").get());
        user.setActivationCode(UUID.randomUUID().toString());
        save(user);
        sendActivationEmail(user);
        return user;
    }

    public void sendActivationEmail(User user){

    }
    public User save(User user){
        return userRepository.save(user);
    }


    @Transactional
    public void saveUsers(User... users){
        for(User user: users){
            logger.info("Saving user: "+user.getEmail());
            userRepository.save(user);
        }
    }
}