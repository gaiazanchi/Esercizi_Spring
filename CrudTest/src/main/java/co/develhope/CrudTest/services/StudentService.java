package co.develhope.CrudTest.services;

import co.develhope.CrudTest.entities.Student;
import co.develhope.CrudTest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student getSingle(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isEmpty()) return null;
        return optionalStudent.get();
    }

    public Student update(Integer id, Student student) {
        Student oldStudent = getSingle(id);
        if(oldStudent == null) return null;
        oldStudent.setName(student.getName());
        oldStudent.setSurname(student.getSurname());
        oldStudent.setWorking(student.isWorking());
        return oldStudent;
    }

    public Student updateWorking(Integer id, boolean working) {
        Student student = getSingle(id);
        if(student == null) return null;
        student.setWorking(working);
        return student;
    }
}
