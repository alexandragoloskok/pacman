package com.pacman.data.controller

import com.pacman.data.domain.User
import com.pacman.data.repository.UserRepository
import com.pacman.model.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@RestController
@RequestMapping("top")
class TopController {

    @Autowired
    UserRepository userRepository

    UserDto userDtoFromUser(User user){
        new UserDto(
                login: user.login,
                score: user.score
        )
    }

    @GetMapping
    public List<UserDto> list() {

        userRepository
                .findAllSort()
                .collect { userDtoFromUser(it) }
    }

    @PutMapping
    public void update(@RequestBody long score, Principal principal){
        userRepository.findByLogin(principal.name).score=score
    }
}

