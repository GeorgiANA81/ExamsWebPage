package com.exams.backend;

import com.exams.backend.entity.RoleEntity;
import com.exams.backend.entity.UserEntity;
import com.exams.backend.service.UserService;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
@Configuration
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Configuration
    static class Config implements WebMvcConfigurer {

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            argumentResolvers.add(new SpecificationArgumentResolver());
        }

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Override
    public void run(String... params) throws Exception {
        UserEntity teacher = new UserEntity();
        teacher.setEmail("toporceanu.vasile@example.com");
        teacher.setPassword("123");
        teacher.setAcademicYear(null);
        teacher.setName("Toporceanu Vasile");
        teacher.setRoles(new ArrayList<RoleEntity>(Arrays.asList(RoleEntity.ROLE_TEACHER)));

        userService.signup(teacher);
    }
}
