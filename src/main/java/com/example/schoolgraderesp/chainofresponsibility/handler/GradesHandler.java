package com.example.schoolgraderesp.chainofresponsibility.handler;

import com.example.schoolgraderesp.dtos.GradesRequest;
import com.example.schoolgraderesp.dtos.GradesResponse;

public interface GradesHandler {
    void setNext(GradesHandler next);
    GradesResponse handle(GradesRequest gradesRequest);
}
