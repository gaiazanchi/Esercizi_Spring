package co.develhope.nosql.controllers;

import co.develhope.nosql.entities.User;
import co.develhope.nosql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public User createUser(@RequestBody User user){
        user.setId(null);
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUsers(@PathVariable String id) throws Exception{
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new Exception("User is not found");
        }
    }

    @PutMapping("/{id}")
    public User editUser(@PathVariable String id, @RequestBody  User user) throws Exception{
        if(!userRepository.existsById(id)) throw new Exception("User is not found");
        user.setId(id);
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userRepository.deleteById(id);
    }

    @GetMapping("")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
