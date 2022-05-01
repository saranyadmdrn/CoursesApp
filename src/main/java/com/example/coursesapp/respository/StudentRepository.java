package com.example.coursesapp.respository;

import com.example.coursesapp.dataModel.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT c.students from Course c where c.courseId=:courseId")
    List<Student> findStudentsByCourseId(Long courseId);

}
