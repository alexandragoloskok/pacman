package com.pacman

import com.pacman.data.domain.User
import com.pacman.data.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

@SpringBootApplication
class PacmanApplication {

    @Autowired
    UserRepository userRepository

    static void main(String[] args) {
        SpringApplication.run(PacmanApplication, args)
    }

    @EventListener
    void start(ContextRefreshedEvent event) {
//        userRepository.save(new User(
//                login: 'Vasya',
//                password: '1234',
//                score: 256
//        ))
    }



}
