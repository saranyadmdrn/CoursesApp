package com.example.coursesapp.daoImpl;

import com.example.coursesapp.dao.CourseDao;
import com.example.coursesapp.dao.StudentDao;
import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;
import com.example.coursesapp.respository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("courseDao")
//CourseDaoImpl can also implement GenericDao directly
//However,@component annotation is needed to resolve the bean definition in service impl
public class CourseDaoImpl implements CourseDao {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long courseId){
        return courseRepository.findById(courseId);
    }

    @Override
    public void deleteById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public void deleteAll() {
        courseRepository.deleteAll();
    }

    @Override
    public boolean existsById(Long courseId) {
        return courseRepository.existsById(courseId);
    }

}
