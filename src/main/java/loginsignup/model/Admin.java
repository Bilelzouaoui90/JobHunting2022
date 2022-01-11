package loginsignup.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message = "veuillez respecter le model suivant")
    private String mdp;

}
