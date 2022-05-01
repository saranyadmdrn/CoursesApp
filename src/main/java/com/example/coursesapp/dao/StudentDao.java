package com.example.coursesapp.dao;

import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;

import java.util.List;

public interface StudentDao extends CrudDao<Student> {
    List<Student> findStudentsUsingCourseId(Long courseId);

    void addStudentToCourse(Long courseId, List<Student> students);

    void deleteStudentFromCourse(Long courseId, Long studentId);
}
