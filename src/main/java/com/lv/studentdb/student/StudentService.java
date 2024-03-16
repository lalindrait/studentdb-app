package com.lv.studentdb.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
    return studentRepository.findAll(); 
    
}


    public void addNewStudent(Student student) {
       Optional<Student> studentByName = studentRepository.findStudentByName(student.getName());

       if (studentByName.isPresent()) {
        throw new IllegalStateException("Name Already in the database");
       }

       studentRepository.save(student);

        // System.out.println(student);

    }


    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email, Integer age) {
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalStateException(
            "student with id " + studentId + " does not exist" ));

            if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
                student.setName(name);
            }

            if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
                student.setEmail(email);
            }

            if (age != null && !Objects.equals(student.getAge(), age)) {
                student.setAge(age);
            }
    }

  

}
