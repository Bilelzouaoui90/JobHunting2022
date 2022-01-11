package loginsignup.repository;


import loginsignup.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
/*

    public List<Entreprise> findByRegion(String reg);
    @Query(value = "Select e from Entreprise m where e.region= :region and (e.nomEnt LIKE :pseudo )")

    public List<Entreprise> search(@Param("spec") String region,@Param("pseudo") String pseudo);

*/
}
