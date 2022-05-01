package com.example.coursesapp.service;

import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course createCourse(Course course);

    List<Course> findAllCourses();

    Optional<Course> findCourseById(Long courseId);

    void deleteCourseById(Long courseId);

    void deleteAllCourses();

    boolean ifCourseExists(Long courseId);

    Course updateCourse(Long courseId, Course course);
}
