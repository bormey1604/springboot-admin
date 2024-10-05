package com.techgirl.spring_admin_client.service;

import com.techgirl.spring_admin_client.model.Student;
import com.techgirl.spring_admin_client.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student insertStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public void removeStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student, int id) {
        Optional<Student> studentOptional = getStudentById(id);

        if (studentOptional.isPresent()) {
            Student stud = studentOptional.get();
            stud.setName(student.getName());
            stud.setAge(student.getAge());

            return studentRepository.save(stud);
        }

        return null;
    }

    public List<Student> searchStudents(String search) {
        return studentRepository.searchStudents(search);
    }
}
