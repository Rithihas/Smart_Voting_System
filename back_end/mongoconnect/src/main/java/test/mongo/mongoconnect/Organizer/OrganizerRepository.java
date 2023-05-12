package test.mongo.mongoconnect.Organizer;



//import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizerRepository extends MongoRepository<Organizer, String> {

  public Organizer findByUsername(String username);
  //public List<Organizer> findByLastName(String lastName);

}