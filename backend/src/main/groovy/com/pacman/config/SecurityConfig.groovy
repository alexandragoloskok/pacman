package com.pacman.config

import com.pacman.data.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    PasswordEncoder passwordEncoder
    @Autowired
    UserDetailsService userDetailsService

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

        http
                .authorizeRequests()
                .antMatchers( '/login', '/registration', '/top',
                        '/', '/game', '/game/map', '/game/map/key')
                .permitAll()

                .anyRequest().authenticated()

        http
                .formLogin()
                .successHandler { req, res, a -> res.status = 200 }
                .failureHandler { req, res, a -> res.status = 401 }

                .and()
                .exceptionHandling()
                .accessDeniedHandler { req, res, a -> res.status = 401 }
    }
}

@Component
class CustomUserDetailsService implements UserDetailsService{

     @Bean
     PasswordEncoder passwordEncoder(){
         new BCryptPasswordEncoder()  //реализация интерфейса
     }

    @Autowired UserRepository userRepositoriy
    @Autowired PasswordEncoder passwordEncoder

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        def entry = userRepositoriy.findByLogin(username)

        if (entry) {
            return new User(username, entry.password, [])
        }

        throw new UsernameNotFoundException("Invalid user")
    }
}


