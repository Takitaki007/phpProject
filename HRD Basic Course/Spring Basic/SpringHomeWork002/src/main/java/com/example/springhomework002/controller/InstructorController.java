package com.example.springhomework002.controller;

import com.example.springhomework002.model.Instructor;
import com.example.springhomework002.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<?> addInstructor(@RequestBody Instructor instructor) {
        instructorService.addInstructor(instructor);
        return ResponseEntity.ok(instructor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstructor(@PathVariable int id) {
        return ResponseEntity.ok(instructorService.getInstructorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable int id, @RequestBody Instructor instructor) {
        instructor.setInstructorId(id);
        instructorService.updateInstructor(instructor);
        return ResponseEntity.ok(instructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable int id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.ok("Instructor deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }
}