package com.example.usermanagement.component;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String modeType;

    @Override
    public void run(String... args) {
        if (Objects.equals("create", modeType))
            addAdmin();
    }

    private void addAdmin() {
        userRepository.save(
                User.builder()
                        .name("Nobila")
                        .email(adminEmail)
                        .password(passwordEncoder.encode(adminPassword))
                        .enabled(true)
                        .build());
    }
}
