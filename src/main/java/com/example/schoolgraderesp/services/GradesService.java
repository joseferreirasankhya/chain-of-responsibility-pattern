package com.example.schoolgraderesp.services;

import com.example.schoolgraderesp.chainofresponsibility.handler.GradesHandler;
import com.example.schoolgraderesp.dtos.GradesRequest;
import com.example.schoolgraderesp.dtos.GradesResponse;
import org.springframework.stereotype.Service;

@Service
public class GradesService {
    private final GradesHandler gradesChain;

    public GradesService(GradesHandler gradesChain) {
        this.gradesChain = gradesChain;
    }

    public GradesResponse evaluate(GradesRequest gradesRequest) throws IllegalAccessException {
        if ((gradesRequest.grade()) < 0 || (gradesRequest.grade() > 10)) {
            throw new IllegalAccessException("Nota não pode ser menor que 0 e maior que 10");
        }

        return gradesChain.handle(gradesRequest);
    }
}
