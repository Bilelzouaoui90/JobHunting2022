package loginsignup.service;


import loginsignup.model.Entreprise;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EntrepriseService {

    public Entreprise add_entreprise(Entreprise e);
    public List<Entreprise> findAll();
    public Optional<Entreprise> find_entreprise (long id);
    public void delete_entreprise(long id);
    public Entreprise update_entreprise(Entreprise e);
    Page<Entreprise> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    void setImage(String fileName);


    /*
    public List<Entreprise> recherche_region(String reg);
    public List<Entreprise> recherche_advanced(String reg,String pseudo);*/

}
