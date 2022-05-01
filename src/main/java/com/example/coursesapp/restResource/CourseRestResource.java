package com.example.coursesapp.restResource;

import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;
import com.example.coursesapp.service.CourseService;
import com.example.coursesapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseRestResource {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        try{
            List<Course> listOfCourses = courseService.findAllCourses();
            if(listOfCourses.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listOfCourses, HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable(value="courseId") Long id){
        try{
            Optional<Course> course = courseService.findCourseById(id);

            if(course.isPresent()) {
                return ResponseEntity.ok().body(course.get());
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        try{
            Course result = courseService.createCourse(course);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable(value = "courseId") Long courseId, @RequestBody Course course){
        try{
            if(courseService.ifCourseExists(courseId)) {
                Course result = courseService.updateCourse(courseId, course);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable(value="courseId") Long courseId){
        try{
            courseService.deleteCourseById(courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
