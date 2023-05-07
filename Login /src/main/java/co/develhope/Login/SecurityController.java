package co.develhope.Login;

import co.develhope.Login.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/login")
    public LoginRTO login(@RequestBody LoginDTO loginDTO) throws Exception{
        LoginRTO loginRto = securityService.login(loginDTO);
        if(loginRto == null) throw new Exception("Cannot login");
        return loginRto;
    }

    @PostMapping("password-request")
    public void passwordRequest(@RequestBody RequestPasswordDTO requestPasswordDTO) throws Exception{
        try {
            securityService.request(requestPasswordDTO);
        }catch (Exception e){

        }

    }

    @PostMapping("password-restore")
    public void passwordRestore(@RequestBody RestorePasswordDTO restorePasswordDTO) throws Exception{
        securityService.restore(restorePasswordDTO);

    }

    /*
    POST localhost:8080/auth/signup
    {
        "name": "Carlo",
        "surname": "Casiglia",
        "email": "carlo.casiglia@gmail.com",
        "password": "prova"
     }
     */
    @PostMapping("/signup")
    public void signup(@RequestBody SignupDTO signupDTO) throws Exception {
        securityService.signup(signupDTO);
    }

    @PostMapping("/signup/activation")
    public void signup(@RequestBody SignupActivationDTO signupActivationDTO) throws Exception {
        securityService.activate(signupActivationDTO);
    }

}
