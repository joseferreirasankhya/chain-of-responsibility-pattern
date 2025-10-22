package com.example.schoolgraderesp.chainofresponsibility.concretehandlers;

import com.example.schoolgraderesp.chainofresponsibility.handler.AbstractGradesHandler;
import com.example.schoolgraderesp.dtos.GradesRequest;
import com.example.schoolgraderesp.dtos.GradesResponse;
import org.springframework.stereotype.Component;

@Component
public class PrincipalGradesHandler extends AbstractGradesHandler {

    @Override
    public GradesResponse handle(GradesRequest gradesRequest) {
        return new GradesResponse(
                "Diretor: acionando aluno, seus responsáveis e professor da matéria!",
                "Principal");
    }
}
