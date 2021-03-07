package be.thomasmore.meeting.repositories;

import be.thomasmore.meeting.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
}
