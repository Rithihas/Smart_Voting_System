package test.mongo.mongoconnect;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface VoterRepository extends MongoRepository<Voter, String> {

 

}