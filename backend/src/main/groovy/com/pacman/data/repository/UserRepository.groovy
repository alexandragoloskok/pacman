package com.pacman.data.repository

import com.pacman.data.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository extends JpaRepository<User, Long> {

    @Query('select u from User u order by u.score desc')
    List<User> findAllSort()

    User findByLogin(String login)

}
