package com.pacman.data.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="users")
class User {
    @Id
    @GeneratedValue
    Long id
    @Column
    String login
    @Column
    String password
    @Column
    Long score
}
