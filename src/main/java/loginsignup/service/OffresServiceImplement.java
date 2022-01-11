package loginsignup.service;


import loginsignup.model.Offre;
import loginsignup.repository.OffresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffresServiceImplement implements OffresService {
    @Autowired
    private OffresRepository agent;

    @Override
    public Offre add_offres(Offre offre) {
        return agent.save(offre);
    }

    @Override
    public List<Offre> findAll() {
        return (List<Offre>) agent.findAll();
    }

    @Override
    public Offre update_offre(Offre e) {
        return agent.save(e);
    }

    @Override
    public Page<Offre> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.agent.findAll(pageable);
    }


    /*@Override
    public List<Offre> search_domaine(String dom) {
        return agent.findByDomaine(dom);
    }

    @Override
    public List<Offre> advanced_search(String dom, String pseudo) {
        return agent.search(dom, "%"+pseudo+"%");
    }*/


    @Override
    public void delete_offre(long id) {
        agent.deleteById(id);
    }
    @Override
    public Optional<Offre> find_offre(long id) {
        return agent.findById(id);
    }
}
