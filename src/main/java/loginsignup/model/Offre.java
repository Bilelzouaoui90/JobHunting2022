package loginsignup.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter

@Entity
@Table(name = "offres")
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @NotEmpty
    @Size(min = 3,max = 10,message = "veuillez respecter le model suivant")
    private String poste;

    @NotEmpty
    @Size(min = 3,message = "veuillez respecter le model suivant")
    private String Description;

    @NotEmpty
    @Size(min = 3,max = 100,message = "veuillez respecter le model suivant")
    private String Salaire;

    private String motscle;

    @NotEmpty
    @Size(min = 3,max = 100,message = "veuillez respecter le model suivant")
    private String deadline;

    private String Domaine;

  @ManyToOne(optional = false)
    @JoinColumn(name = "entreprise_id", referencedColumnName = "id")
    private loginsignup.model.Entreprise entreprise;

    public Offre(){
    this.entreprise=new loginsignup.model.Entreprise();
    }







}
