package com.educationalmanage.config;

import com.educationalmanage.controller.interceptor.AdminInterceptor;
import com.educationalmanage.controller.interceptor.LoginInterceptor;
import com.educationalmanage.controller.interceptor.StudentInterceptor;
import com.educationalmanage.controller.interceptor.TeacherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Autowired
//    private LoginInterceptor loginInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private TeacherInterceptor teacherInterceptor;
    @Autowired
    private StudentInterceptor studentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList<>();
        patterns.add("/user/login");
        List<String> admin = new ArrayList<>();
        admin.add("");
        List<String> teacher = new ArrayList<>();
        teacher.add("");
        List<String> student = new ArrayList<>();
        student.add("");
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(patterns);
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**").excludePathPatterns(admin);
        registry.addInterceptor(teacherInterceptor).addPathPatterns("/teacher/**").excludePathPatterns(teacher);
        registry.addInterceptor(studentInterceptor).addPathPatterns("/student/**").excludePathPatterns(student);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
//                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE", "HEAD")
                .allowedHeaders("*")
                .exposedHeaders("*")
                .maxAge(3600);
    }
}
