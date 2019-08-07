package com.pacman.data.controller

import com.pacman.Pacman
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("game")
class GameController {

Pacman pacman = new Pacman()

    @GetMapping("map")
    public ArrayList map() {
        pacman.getMap()
    }

}
