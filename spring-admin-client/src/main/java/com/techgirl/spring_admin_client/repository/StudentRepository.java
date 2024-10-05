package com.techgirl.spring_admin_client.repository;

import com.techgirl.spring_admin_client.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from student where cast(id as text) = ?1 or name like %?1% or cast(age as text) = ?1",nativeQuery = true)
    List<Student> searchStudents(String search);
}
