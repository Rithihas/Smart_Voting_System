package example.voterbackend;

import org.springframework.data.mongodb.repository.MongoRepository;

import example.voterbackend.Voter;

public interface VoterRepository extends MongoRepository<Voter, String> {

 

}