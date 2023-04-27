package co.develhope.redis;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private String passwordEncrypted;

    private String domicileAddress;
    private String domicileCity;
    private String domicileNumber;
    private String domicileState;

    private String fiscalCode;

}
