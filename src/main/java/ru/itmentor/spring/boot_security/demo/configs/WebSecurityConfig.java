package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

// настройка секьюрности по определенным URL, а также настройка UserDetails и GrantedAuthority
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    @Autowired
    private DataSource dataSource;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    // Настройка HttpSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    // .permitAll() - Запросы не требуют авторизации и являются общедоступной конечной точкой
                    .antMatchers("/", "/index", "/user").permitAll()
                    // .hasAnyRole - доступны пользователям с указанной ролью
                    .antMatchers("/user").hasAnyRole( "USER")
                    .antMatchers("/admin").hasAnyRole("ADMIN")
                    // если не выполняется выше, то требование аутентификация
                    .anyRequest().authenticated()
                .and()
                    // поддержка аутентификации /login и действие после
                    .formLogin().successHandler(successUserHandler).permitAll()
                .and()
                    // Обеспечивает выход из системы /logout
                    .logout().permitAll();
    }

    // переопределение запросов на получение полномочий и enabled
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username, r.role from users u inner join roles r on u.id = r.user_id where u.username=?");
    }

    // аутентификация inMemory
    //то, что будет интерпретироваться системой как пользователь
    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER") // будет преобразована в экземпляр GrantedAuthority - сущность, описывающая права юзера
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/
}