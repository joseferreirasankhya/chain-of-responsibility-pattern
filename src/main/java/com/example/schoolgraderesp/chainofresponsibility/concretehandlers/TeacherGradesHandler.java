package com.example.schoolgraderesp.chainofresponsibility.concretehandlers;

import com.example.schoolgraderesp.chainofresponsibility.handler.AbstractGradesHandler;
import com.example.schoolgraderesp.dtos.GradesRequest;
import com.example.schoolgraderesp.dtos.GradesResponse;
import org.springframework.stereotype.Component;

@Component
public class TeacherGradesHandler extends AbstractGradesHandler {

    @Override
    public GradesResponse handle(GradesRequest gradesRequest) {
        if (gradesRequest.grade() >= 8 && gradesRequest.grade() <= 10) {
            return new GradesResponse(
                    "Professor: nota validada, aluno aprovado!",
                    "Teacher");
        }

        if (gradesRequest.grade() >= 6 && gradesRequest.grade() < 8) {
            return new GradesResponse(
                    "Professor: chamando aluno para reunião",
                    "Teacher"
            );
        }

        return forward(gradesRequest);
    }
}
