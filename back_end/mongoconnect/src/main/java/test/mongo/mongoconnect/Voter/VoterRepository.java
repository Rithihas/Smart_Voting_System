package test.mongo.mongoconnect.Voter;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface VoterRepository extends MongoRepository<Voter, String> {

 

}