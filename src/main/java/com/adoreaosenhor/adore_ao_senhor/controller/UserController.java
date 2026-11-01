package com.adoreaosenhor.adore_ao_senhor.controller;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRepository;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.ReadUserDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.UpdateUserDTO;
import com.adoreaosenhor.adore_ao_senhor.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if(userService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping
    public ResponseEntity<List<ReadUserDTO>> listUsersByInstitutions(Authentication authentication, Pageable pageable) {
        var authenticatedUser = (User) authentication.getPrincipal();
        Long institutionId = authenticatedUser.getInstituicao().getId();

        List<ReadUserDTO> users = userService.findAllByInstitutions(institutionId, pageable);

        return ResponseEntity.ok(users);

    }

    @PatchMapping
    @Transactional
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody @Valid UpdateUserDTO data, Authentication authentication) {
        User authenticatedUser = (User) authentication.getPrincipal();
        UpdateUserDTO dataUpdated = userService.updateData(data, authenticatedUser);

        return ResponseEntity.ok(dataUpdated);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadUserDTO> activateUser(@PathVariable Long id) {
        return userService.activateUser(id);
    }

}
