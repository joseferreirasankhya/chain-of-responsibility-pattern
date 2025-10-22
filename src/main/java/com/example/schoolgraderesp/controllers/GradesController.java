package com.example.schoolgraderesp.controllers;

import com.example.schoolgraderesp.dtos.GradesRequest;
import com.example.schoolgraderesp.dtos.GradesResponse;
import com.example.schoolgraderesp.services.GradesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/school")
public class GradesController {
    private final GradesService gradesService;

    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping()
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong!");
    }

    @PostMapping()
    public ResponseEntity<GradesResponse> checkGrade(@RequestBody GradesRequest gradesRequest) throws IllegalAccessException {
        GradesResponse gradesResponse = gradesService.evaluate(gradesRequest);
        return ResponseEntity.accepted().body(gradesResponse);
    }
}
