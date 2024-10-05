package com.techgirl.spring_admin_client.controller;

import com.techgirl.spring_admin_client.model.Student;
import com.techgirl.spring_admin_client.service.StudentService;
import com.techgirl.student_app.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<Response> getStudents() {

        List<Student> students = studentService.getAllStudents();

        return new ResponseEntity<>(
                new Response("200","success",students),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getStudent(@PathVariable int id) {
        Optional<Student> student = studentService.getStudentById(id);

        if (student.isPresent()) {

            return new ResponseEntity<>(
                    new Response("200","success",student.get()),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                new Response("404","student with id: "+id+" not found",null),
                HttpStatus.NOT_FOUND
        );

    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<Response> deleteStudent(@PathVariable int id) {
        Optional<Student> student = studentService.getStudentById(id);

        if (student.isPresent()) {

            studentService.removeStudent(id);

            return new ResponseEntity<>(
                    new Response("200","success remove student with id: "+id,null),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                new Response("404","student with id: "+id+" not found",null),
                HttpStatus.NOT_FOUND
        );

    }

    @PutMapping("update/{id}")
    public ResponseEntity<Response> update(@RequestBody Student student,@PathVariable int id) {
        Student updateStudent = studentService.updateStudent(student,id);

        if (updateStudent != null) {

            return new ResponseEntity<>(
                    new Response("200","success update student with id: "+id,updateStudent),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                new Response("404","student with id: "+id+" not found",null),
                HttpStatus.NOT_FOUND
        );

    }

    @GetMapping("/search")
    public ResponseEntity<Response> searchStudents(@RequestParam(name = "key") String search) {
        List<Student> searchResults = studentService.searchStudents(search);

        if (searchResults.isEmpty()) {
            return new ResponseEntity<>(
                    new Response("404","student not found",null),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                new Response("200","success",searchResults),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response> insertStudent(@RequestBody Student student) {
        Student savedStudent = studentService.insertStudent(student);

        return new ResponseEntity<>(
                new Response("200","success",savedStudent),
                HttpStatus.OK
        );
    }
}
