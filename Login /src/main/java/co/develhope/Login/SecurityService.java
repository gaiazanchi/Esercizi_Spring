package co.develhope.Login;

import co.develhope.Login.entities.Role;
import co.develhope.Login.entities.Roles;
import co.develhope.Login.entities.User;
import co.develhope.Login.repositories.RoleRepository;
import co.develhope.Login.repositories.UserRepository;
import co.develhope.Login.entities.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class SecurityService {

    public static final String JWT_SECRET = "b3f4db21-599c-41db-b531-77a951a3a67e";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MailNotificationService mailNotificationService;

    public User request(RequestPasswordDTO requestPasswordDTO) throws Exception{
        User userFromDB = userRepository.findByEmail(requestPasswordDTO.getEmail());
        if(userFromDB == null) throw new Exception("Cannot find user");
        userFromDB.setPasswordResetCode(UUID.randomUUID().toString());
        mailNotificationService.sendPasswordResetMail(userFromDB);
        return userRepository.save(userFromDB);

    }
    public User restore(RestorePasswordDTO restorePasswordDTO) throws Exception{
        User userFromDB = userRepository.findByPasswordResetCode(restorePasswordDTO.getResetPasswordCode());
        if(userFromDB == null) throw new Exception("Cannot find user");
        userFromDB.setPassword(passwordEncoder.encode(restorePasswordDTO.getNewPassword()));
        userFromDB.setPasswordResetCode(null);

        //I am activating the user
        userFromDB.setActive(true);
        userFromDB.setActivationCode(null);

        return userRepository.save(userFromDB);
    }

    public LoginRTO login(LoginDTO loginDTO){
        if(loginDTO == null) return null;
        User userFromDB = userRepository.findByEmail(loginDTO.getEmail());
        if(userFromDB == null || !userFromDB.isActive()) return null;

        boolean canLogin = this.canUserLogin(userFromDB, loginDTO.getPassword());
        if(!canLogin) return null;

        String JWT = generateJWT(userFromDB);

        //userFromDB.setJwtCreatedOn(LocalDateTime.now());
        //userRepository.save(userFromDB);

        //userFromDB.setPassword(null);
        LoginRTO out = new LoginRTO();
        out.setJWT(JWT);
        out.setUser(userFromDB);

        return out;
    }

    public boolean canUserLogin(User user, String password){
        return passwordEncoder.matches(password, user.getPassword());
    }

    //https://www.baeldung.com/java-date-to-localdate-and-localdatetime
    static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static String getJWT(User user){
        Date expiresAt = convertToDateViaInstant(LocalDateTime.now().plusDays(15));
        String[] roles = user.getRoles().stream().map(role -> role.getName()).toArray(String[]::new);
        return JWT.create()
                .withIssuer("develhope-demo")
                .withIssuedAt(new Date())
                .withExpiresAt(expiresAt)
                .withClaim("roles",String.join(",",roles)) //funziona su nuove versioni Java (17)
                .withClaim("id", user.getId())
                .sign(Algorithm.HMAC512(JWT_SECRET));
    }

    public String generateJWT(User user) {
        String JWT = getJWT(user);

        user.setJwtCreatedOn(LocalDateTime.now());
        userRepository.save(user);

        return JWT;
    }

    public User signup(SignupDTO signupDTO) throws Exception { //SignupDTO rappresenta in Java l'oggetto body su Postman

        if (userRepository.existsByEmail(signupDTO.getEmail())) throw new Exception("Email " + signupDTO.getEmail() + " already exist");
        //User userInDB = userRepository.findByEmail(signupDTO.getEmail());
        //if(userInDB != null) throw new Exception("User already exist");

        // creo nuovo user e setto i parametri necessari
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setSurname(signupDTO.getSurname());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));// la password viene codificata con PasswordEncoder
        //user.setActive(false); // aggiunto parametro in User class

        //genera un codice univoco di 36 caratteri
        // UUID = Universal Unique IDentifier
        user.setActivationCode(UUID.randomUUID().toString());

        Set<Role> roles = new HashSet<>();

        Optional<Role> userRole =roleRepository.findByName(Roles.REGISTERED);
        if(!userRole.isPresent()) throw new Exception("Cannot set user role");
        roles.add(userRole.get());
        user.setRoles(roles);

        mailNotificationService.sendActivationEmail(user);// invio mail di attivazione
        return userRepository.save(user); // ritorniamo l'user salvato
    }

    public User activate(SignupActivationDTO signupActivationDTO) throws Exception {
        User user = userRepository.findByActivationCode(signupActivationDTO.getActivationCode());
        if(user == null) throw new Exception("User Not found");
        user.setActive(true);
        user.setActivationCode(null);
        return userRepository.save(user);
    }

}
