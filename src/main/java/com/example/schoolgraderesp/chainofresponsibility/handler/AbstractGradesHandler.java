package com.example.schoolgraderesp.chainofresponsibility.handler;

import com.example.schoolgraderesp.dtos.GradesRequest;
import com.example.schoolgraderesp.dtos.GradesResponse;

public abstract class AbstractGradesHandler implements GradesHandler {
    private GradesHandler next = null;

    public void setNext(GradesHandler next) {
        this.next = next;
    }

    protected GradesResponse forward(GradesRequest gradesRequest) {
        if (next != null)
            return next.handle(gradesRequest);

        return handle(gradesRequest);
    }
}
