package com.example.coursesapp.serviceImpl;

import com.example.coursesapp.dao.StudentDao;
import com.example.coursesapp.dataModel.Course;
import com.example.coursesapp.dataModel.Student;
import com.example.coursesapp.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @Override
    public Student createStudent(Student student) {
        return studentDao.create(student);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public Optional<Student> findStudentById(Long studentId) {
        return studentDao.findById(studentId);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentDao.deleteById(studentId);
    }

    @Override
    public void deleteAllStudents() {
        studentDao.deleteAll();
    }

    @Override
    public boolean ifStudentExists(Long studentId) {
        return studentDao.existsById(studentId);
    }

    @Override
    public List<Student> findAllStudentsUsingCourseId(Long courseId) {
        return studentDao.findStudentsUsingCourseId(courseId);
    }

    @Override
    public void addStudentToCourse(Long courseId, List<Student> students) {
        studentDao.addStudentToCourse(courseId, students);
    }

    @Override
    public void deleteStudentFromCourse(Long courseId, Long studentId) {
        studentDao.deleteStudentFromCourse(courseId, studentId);
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        Optional<Student> existingSource = studentDao.findById(studentId);
        existingSource.get().setStudentName(student.getStudentName());
        return studentDao.create(existingSource.get());
    }
}
