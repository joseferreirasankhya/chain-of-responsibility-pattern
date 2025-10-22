package com.example.schoolgraderesp.chainofresponsibility.config;

import com.example.schoolgraderesp.chainofresponsibility.concretehandlers.ManagerGradesHandler;
import com.example.schoolgraderesp.chainofresponsibility.concretehandlers.PrincipalGradesHandler;
import com.example.schoolgraderesp.chainofresponsibility.concretehandlers.TeacherGradesHandler;
import com.example.schoolgraderesp.chainofresponsibility.handler.GradesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GradeConfig {
    @Bean
    @Primary
    public GradesHandler gradeChain(
            TeacherGradesHandler teacherGradesHandler,
            ManagerGradesHandler managerGradesHandler,
            PrincipalGradesHandler principalGradesHandler
    ) {
        teacherGradesHandler.setNext(managerGradesHandler);
        managerGradesHandler.setNext(principalGradesHandler);
        return teacherGradesHandler;
    }
}
