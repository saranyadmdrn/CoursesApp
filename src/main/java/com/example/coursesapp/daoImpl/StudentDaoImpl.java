package com.example.coursesapp.daoImpl;

import com.example.coursesapp.dao.StudentDao;
import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;
import com.example.coursesapp.respository.CourseRepository;
import com.example.coursesapp.respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("studentDao")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public boolean existsById(Long studentId) {
        return studentRepository.existsById(studentId);
    }

    public List<Student> findStudentsUsingCourseId(Long courseId) {
        List<Student> listOfStudents = studentRepository.findStudentsByCourseId(courseId);
        return listOfStudents;
    }

    @Override
    public void addStudentToCourse(Long courseId, List<Student> students) {
        //students should already exist..
        Course courseObj = courseRepository.getById(courseId);
        for(Student student : students){
            courseObj.addStudent(student);
        }
        courseRepository.save(courseObj);
    }

    @Override
    public void deleteStudentFromCourse(Long courseId, Long studentId) {
        Course courseObj = courseRepository.getById(courseId);
        courseObj.removeStudent(studentId);
        courseRepository.save(courseObj);
    }
}
