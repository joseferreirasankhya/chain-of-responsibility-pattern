package com.example.schoolgraderesp.chainofresponsibility.concretehandlers;

import com.example.schoolgraderesp.chainofresponsibility.handler.AbstractGradesHandler;
import com.example.schoolgraderesp.dtos.GradesRequest;
import com.example.schoolgraderesp.dtos.GradesResponse;
import org.springframework.stereotype.Component;

@Component
public class ManagerGradesHandler extends AbstractGradesHandler {

    @Override
    public GradesResponse handle(GradesRequest gradesRequest) {
        if (gradesRequest.grade() >= 4 && gradesRequest.grade() < 6) {
            return new GradesResponse(
                    "Supervisor: Acionando aluno e responsáveis!",
                    "Manager");
        }

        return forward(gradesRequest);
    }
}
