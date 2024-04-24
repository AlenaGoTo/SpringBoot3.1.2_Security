package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    // Контроллер представления - возвращает указанное представление. Это избавляет от необходимости писать контроллер,
    // когда вы хотите перенаправить запрос напрямую в представление
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/index").setViewName("index");
    }
}
