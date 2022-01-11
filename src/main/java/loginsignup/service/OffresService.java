package loginsignup.service;


import loginsignup.model.Offre;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface OffresService {
    public Offre add_offres(Offre offre);
    public List<Offre> findAll();

    public Optional<Offre> find_offre (long id);


    public void delete_offre(long id);
    public Offre update_offre(Offre e);
    Page<Offre> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    /*public  List<Offre> search_domaine(String dom);
    public  List<Offre> advanced_search(String dom,String pseudo);*/
}
