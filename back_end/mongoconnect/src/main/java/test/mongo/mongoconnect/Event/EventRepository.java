package test.mongo.mongoconnect.Event;


import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EventRepository extends MongoRepository<Event, String> {

  public Event findByOrgName(String OrgName);


  @Query(value = "{orgDomain : ?0}")
  public List<Event> findByOrgDomain(String orgDomain);

}