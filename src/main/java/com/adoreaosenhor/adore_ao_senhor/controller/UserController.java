package com.adoreaosenhor.adore_ao_senhor.controller;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRepository;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.ReadUserDTO;
import com.adoreaosenhor.adore_ao_senhor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
