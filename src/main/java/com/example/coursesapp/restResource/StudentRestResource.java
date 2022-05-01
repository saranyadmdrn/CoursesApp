package com.example.coursesapp.restResource;

import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;
import com.example.coursesapp.service.CourseService;
import com.example.coursesapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentRestResource {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        try{
            List<Student> listOfStudents = new ArrayList<>();
            studentService.findAllStudents().forEach(listOfStudents::add);
            if(listOfStudents.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listOfStudents, HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value="studentId") Long id){
        Optional<Student> student = studentService.findStudentById(id);

        if(student.isPresent()) {
            return ResponseEntity.ok().body(student.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/students/courses/{courseId}")
    public ResponseEntity<List<Student>> getAllStudentsByCourseId(@PathVariable(value="courseId") Long courseId){
        try {
            if (!courseService.ifCourseExists(courseId)) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            List<Student> listOfStudents = studentService.findAllStudentsUsingCourseId(courseId);
            return new ResponseEntity<>(listOfStudents, HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        try{
            Student result = studentService.createStudent(student);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Add students to course using course id
    @PostMapping("/students/courses/{courseId}")
    public ResponseEntity<HttpStatus> addStudentsToCourse(@PathVariable(value="courseId") Long courseId, @RequestBody List<Student> students){
        try{
            if(courseService.ifCourseExists(courseId)){
                studentService.addStudentToCourse(courseId, students);
                return new ResponseEntity<>(HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "studentId") Long studentId, @RequestBody Student student){
        try{
            if(studentService.ifStudentExists(studentId)){
                Student result = studentService.updateStudent(studentId, student);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            } else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteStudentFromCourse(@PathVariable(value="courseId") Long courseId, @PathVariable(value="studentId") Long studentId){
        try{
            if(courseService.ifCourseExists(courseId)){
                studentService.deleteStudentFromCourse(courseId, studentId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable(value="studentId") Long id){
        try{
            studentService.deleteStudentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

