package co.develhope.loginDemo.salary.controllers;

import co.develhope.loginDemo.salary.entities.CreateSalaryDTO;
import co.develhope.loginDemo.salary.entities.Salary;
import co.develhope.loginDemo.user.utils.Roles;
import org.springframework.web.bind.annotation.*;
import co.develhope.loginDemo.salary.repositories.SalaryRepository;

import java.util.List;


@RestController
@GetMapping("/salary")
public class SalaryController {

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public List<Salary> getAll(@PathVariable Long id){
        return salaryRepository.findAll();
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public Salary getSalaryByUid(@PathVariable Long id){
        return salaryRepository.findByUserId(id);
    }

    @PostMapping("/user/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public Salary createSalary(@PathVariable Long id, @RequestBody CreateSalaryDTO salaryDTO){
        Salary s = new Salary();
        s.setAmount(salaryDTO.getAmount());
        User user = userRepository.findById(id);
        s.setUser(user)
        return salaryRepository.save(s);
    }
}
