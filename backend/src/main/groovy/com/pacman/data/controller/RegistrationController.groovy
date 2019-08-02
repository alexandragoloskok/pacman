package com.pacman.data.controller

import com.pacman.data.domain.User
import com.pacman.data.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("registration")
class RegistrationController {
    @Autowired
    UserRepository userRepository

    @Autowired
    PasswordEncoder passwordEncoder

    @PostMapping
    public void createNewEntry(@RequestBody Map<String,String> user){
        userRepository.save(new User(
                login:      user['username'],
                password:   passwordEncoder.encode(user['password']),
                score:      0
        ))
    }

}
