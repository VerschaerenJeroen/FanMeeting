package be.thomasmore.league.repositories;

import be.thomasmore.league.model.Champion;
import be.thomasmore.league.model.Faction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FactionRepository extends CrudRepository<Faction, Integer> {
    List<Faction> findAllBy();

    @Query("select f from Faction f where (?1 is null or upper(f.factionName) like upper(concat('%',?1,'%')))")
    List<Faction> findByKeyword(String name);
}
