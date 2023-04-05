package co.develhope.Email02.students;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    static List<Student> students = Arrays.asList(
            new Student("1" , "Gaia", "Zanchi", "gaia.zanchi2001@gmail.com"),
            new Student("2" , "Mario", "Rossi", "lore1@gmail.com"),
            new Student("3" , "Giulio", "Verdi", "lore2@gmail.com"),
            new Student("4" , "Giovanna", "Gialli", "lore3@gmail.com")
    );

    public Student getStudentById(String studentId) {
        Optional<Student> studentFromDb = students.stream().filter(elem -> elem.getId().equals(studentId)).findAny();
        if(studentFromDb.isPresent()){
            return studentFromDb.get();
        }else{
            return null;
        }
    }
}
