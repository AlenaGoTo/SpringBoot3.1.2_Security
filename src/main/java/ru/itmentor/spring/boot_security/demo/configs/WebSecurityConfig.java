package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

    // Настройка HttpSecurity для авторизации
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // отключение CSRF защиты (для постмана POST)
            .csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/crud/**")).and()
            .authorizeRequests()
                //не требует аутентификации
                .antMatchers("/registration").not().fullyAuthenticated()
                // .permitAll() - не требуют авторизации и являются общедоступной конечной точкой
                .antMatchers("/", "/index").permitAll()
                // .hasAnyRole - доступны пользователям с указанной ролью
                .antMatchers("/user").hasAnyRole( "USER")
                .antMatchers("/admin/**", "/crud/**").hasAnyRole("ADMIN")
                // все остальное требует аутентификации
            .anyRequest().authenticated()
            .and()
                // поддержка аутентификации /login и действие после
                .formLogin().successHandler(successUserHandler).permitAll()
            .and()
                // Обеспечивает выход из системы /logout с перенаправлением на главную страницу
                .logout().permitAll().logoutSuccessUrl("/");
    }

    // Настройка AuthenticationManagerBuilder для аутентификации
    // переопределение запросов на получение полномочий и enabled
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username, r.role from users u inner join users_roles ur on u.id = ur.user_id inner join roles r on ur.roles_id = r.id where u.username=?");
    }

    /*
    // Контейнер для конфигурации CORS, а также методы проверки фактического источника, методы HTTP и заголовки данного запроса.
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080")); // Список источников, для которых разрешены запросы между источниками,
        configuration.setAllowedMethods(Arrays.asList("GET","POST")); // разрешенные методы HTTP
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

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