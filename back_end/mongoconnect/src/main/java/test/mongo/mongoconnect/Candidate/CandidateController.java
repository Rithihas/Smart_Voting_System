package test.mongo.mongoconnect.Candidate;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin; 


// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController; 
import java.util.Optional;
import org.springframework.http.ResponseEntity;
@RestController
public class CandidateController {
    
    private final CandidateRepository repository;
                                                       
    CandidateController(CandidateRepository repository)
    {
        this.repository = repository;
    }


    // returns all items
    @CrossOrigin
    @GetMapping("/eventcandidates/{event}")
    List<Candidate> all(@PathVariable String event)
    {
    	System.out.println(repository.findByEventName(event));
        return repository.findByEventName(event);
    }
    
    @CrossOrigin
    @PostMapping("/candidates")
    Candidate newCandidate(@RequestBody Candidate newCandidate)
    {
        return repository.save(newCandidate);
    }


    //returning single item
    @CrossOrigin
    @GetMapping("/candidates/{id}")
    Candidate one(@PathVariable String id)
    {
        return repository.findById(id).get();
    }


    @CrossOrigin
    @PutMapping("/candidates/{id}")
    public ResponseEntity<Candidate> addVote(@PathVariable String id) {
        Optional<Candidate> optionalCandidate = repository.findById(id);
        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();
            Integer count = candidate.getVotes() + 1;
       candidate.setVotes(count);
            Candidate updatedCandidate = repository.save(candidate);
            return ResponseEntity.ok(updatedCandidate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



@CrossOrigin
@GetMapping("/candidates/delete/{id}")
String deleteCandidate(@PathVariable String id)
{
    repository.deleteById(id);

    return "deleted successfully";
}



}
