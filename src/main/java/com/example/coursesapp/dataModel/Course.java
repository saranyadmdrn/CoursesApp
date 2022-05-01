package com.example.coursesapp.dataModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Long courseId;

    @Column(name="course_name")
    private String courseName;

    @ManyToMany(targetEntity = Student.class)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;

    public Course() {

    }

    public Course(String courseName){
        this.courseName = courseName;
    }

//    public Course(String courseName, Set<Student> students){
//        this.courseName = courseName;
//        this.students = students;
//    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long id) {
        this.courseId = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        this.students.add(student);
        //student.getCourses().add(this);
    }

    public void removeStudent(Long studentId){
        Student student = this.students.stream().filter(s -> s.getStudentId() == studentId).findFirst().orElse(null);
        if(student != null){
            this.students.remove(student);
        }
        //student.getCourses().remove(this);
    }
}
