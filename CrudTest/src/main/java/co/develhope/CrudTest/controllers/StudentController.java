package co.develhope.CrudTest.controllers;

import co.develhope.CrudTest.entities.Student;
import co.develhope.CrudTest.repositories.StudentRepository;
import co.develhope.CrudTest.services.StudentService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping
    public Student create(@RequestBody @NotNull Student student){
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getList(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getSingle(@PathVariable Integer id){
        return studentService.getSingle(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Integer id, @RequestBody @NotNull Student student){
        return studentService.update(id, student);
    }

    @PutMapping("/{id}/working")
    public Student updateWorking(@PathVariable Integer id, @RequestParam boolean working){
        return studentService.updateWorking(id, working);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        studentRepository.deleteById(id);
    }

}
