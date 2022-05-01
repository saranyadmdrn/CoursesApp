package com.example.coursesapp.respository;

import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
