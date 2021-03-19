package be.thomasmore.meeting.repositories;

import be.thomasmore.meeting.model.Champion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChampionRepository extends CrudRepository<Champion, Integer> {
    List<Champion> findAllBy();

    @Query("select c from Champion c where " +
            "(?1 is null or upper(c.championName) like upper(concat('%',?1,'%'))) and " +
            "(?2 is null or upper(?2) = upper(c.role) or ?2 = 'all')")
    List<Champion> findByFilterQuery(String name, String role);
}
