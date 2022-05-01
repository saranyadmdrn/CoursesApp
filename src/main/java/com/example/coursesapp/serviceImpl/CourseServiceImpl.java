package com.example.coursesapp.serviceImpl;

import com.example.coursesapp.dao.CourseDao;
import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;
import com.example.coursesapp.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    //@Autowired
    public CourseServiceImpl(CourseDao courseDao){
        this.courseDao = courseDao;
    }

    @Override
    public Course createCourse(Course course) {
        return courseDao.create(course);
    }

    @Override
    public List<Course> findAllCourses() {
        List<Course> listOfCourses = new ArrayList<>();
        courseDao.findAll().forEach(listOfCourses::add);
        return listOfCourses;
    }

    @Override
    public Optional<Course> findCourseById(Long courseId) {
        return courseDao.findById(courseId);
    }

    @Override
    public void deleteCourseById(Long courseId) {
        courseDao.deleteById(courseId);
    }

    @Override
    public void deleteAllCourses() {
        courseDao.deleteAll();
    }

    @Override
    public boolean ifCourseExists(Long courseId) {
        return courseDao.existsById(courseId);
    }

    @Override
    public Course updateCourse(Long courseId, Course course) {
        Optional<Course> existingCourse = courseDao.findById(courseId);
        existingCourse.get().setCourseName(course.getCourseName());
        return courseDao.create(existingCourse.get());
    }
}
