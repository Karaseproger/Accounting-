package com.Accounting.Accounting.controllers;

import com.Accounting.Accounting.DTO.UserDto;
import com.Accounting.Accounting.models.Useres;
import com.Accounting.Accounting.repozitory.UseresRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UseresRepository useresRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UseresRepository userRepository, PasswordEncoder passwordEncoder) {
        this.useresRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        Useres useres = new Useres();
        useres.setUsername(userDto.getUsername());
        useres.setPassword(passwordEncoder.encode(userDto.getPassword()));
        useres.setRole(userDto.getRole());
        useresRepository.save(useres);

        return ResponseEntity.ok("Пользователь добавлен");
    }
}
