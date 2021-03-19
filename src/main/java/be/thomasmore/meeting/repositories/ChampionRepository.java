package be.thomasmore.meeting.repositories;

import be.thomasmore.meeting.model.Champion;
import org.springframework.data.repository.CrudRepository;

public interface ChampionRepository extends CrudRepository<Champion, Integer> {
}
