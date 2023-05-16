package test.mongo.mongoconnect.Candidate;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface CandidateRepository extends MongoRepository<Candidate, String> {

//	Optional<Voter> addVote(String id);

@Query(value = "{eventName : ?0}", sort = "{votes : -1}")
public List<Candidate> findByEventName(String eventName);

}
