package com.example.coursesapp.service;

import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> findAllStudents();

    Optional<Student> findStudentById(Long studentId);

    void deleteStudentById(Long studentId);

    void deleteAllStudents();

    boolean ifStudentExists(Long studentId);

    List<Student> findAllStudentsUsingCourseId(Long courseId);

    void addStudentToCourse(Long courseId, List<Student> students);

    void deleteStudentFromCourse(Long courseId, Long studentId);

    Student updateStudent(Long studentId, Student student);
}
