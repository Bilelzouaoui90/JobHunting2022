package loginsignup.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "entreprises")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotEmpty
    @Size(min = 3,max = 10,message = "veuillez respecter le model suivant")
    private String nomEnt;

    @Email
    @NotEmpty
    private String emailEnt;

    @NotEmpty
    @Size(min = 3,max = 10,message = "veuillez respecter le model suivant")
    private String phoneEnt;

    @NotEmpty
    private String region;

    @OneToMany (mappedBy = "entreprise",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Offre> offres;

    private  String image;
    @Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;

        return "../user-photos/" + id + "/" + image;
    }

}
