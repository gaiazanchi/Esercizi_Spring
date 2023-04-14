package co.develhope.CrudTest;

import co.develhope.CrudTest.entities.Student;
import co.develhope.CrudTest.repositories.StudentRepository;
import co.develhope.CrudTest.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void checkStudentWorking() throws Exception{
        Student student = new Student();
        student.setWorking(true);
        student.setName("Gaia");
        student.setSurname("Zanchi");

        Student studentFromDb = studentRepository.save(student);
        assertThat(studentFromDb).isNotNull();
        assertThat(studentFromDb.getId()).isNotNull();

        Student studentFromService = studentService.updateWorking(student.getId(), true);
        assertThat(studentFromService).isNotNull();
        assertThat(studentFromService.getId()).isNotNull();
        assertThat(studentFromService.isWorking()).isTrue();

        Student studentFromFind = studentRepository.findById(studentFromDb.getId()).get();
        assertThat(studentFromFind).isNotNull();
        assertThat(studentFromFind.getId()).isNotNull();
        assertThat(studentFromFind.getId()).isEqualTo(studentFromDb.getId());
        assertThat(studentFromFind.isWorking()).isTrue();

    }

}
