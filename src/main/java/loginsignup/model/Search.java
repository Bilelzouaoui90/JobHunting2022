package loginsignup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Search {


        private String domaine;
        private List<Offre> offreList;
        private String pseudo;
    }


