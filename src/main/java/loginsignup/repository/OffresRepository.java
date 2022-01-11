package loginsignup.repository;


import loginsignup.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffresRepository extends JpaRepository<Offre,Long> {

    /*public List<Offre> findByDomaine(String domaine);

    @Query(value="select m from Doctor m where m.specialite = :spec and (m.firstname Like :pseudo or m.lastname Like :pseudo)")
    public  List<Offre> search(@Param("domaine") String domaine, @Param("pseudo") String pseudo);*/

}
