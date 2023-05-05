package test.mongo.mongoconnect;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VoterController {
    
    private final VoterRepository repository;
                                                       
    VoterController(VoterRepository repository)
    {
        this.repository = repository;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/voters/verifyuser/{username}")
     String verifyUser(@PathVariable String username)
    {
        
        System.out.println(username);

        if(repository.findById(username).isPresent()) {
         System.out.println(username);
            return "{\"UserPresent\" : \"true\"}";

        }
        else 
        return "{\"UserPresent\" : \"false\"}";
    }


    // returns all items
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/voters")
    List<Voter> all()
    {
        return repository.findAll();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @PostMapping("/voters")
    Voter newVoter(@RequestBody Voter newVoter)
    {
        return repository.save(newVoter);
    }


    //returning single item
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/voters/{id}")
    Voter one(@PathVariable String id)
    {
        return repository.findById(id).orElseThrow(()-> new VoterNotFoundException(id));
    }

@CrossOrigin(origins = "http://127.0.0.1:5500/")
   @PutMapping("/voters/{id}")
   Voter replaceVoter(@RequestBody Voter newVoter , @PathVariable String id)
   {
       return repository.findById(id)
       .map(voter -> {
         voter.setUsername(newVoter.getUsername());
         voter.setPassword(newVoter.getPassword());
         voter.setPhotoString(newVoter.getPhotoString());
         return repository.save(voter);
       })
       .orElseGet(() -> {
           
           newVoter.setUsername(id);
         return repository.save(newVoter);
       });
   }

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@DeleteMapping("/voters/{id}")
void deleteVoter(@PathVariable String id)
{
   repository.deleteById(id);
}



}





// curl -X POST ^
//   -H "Content-Type: application/json" ^
//   -d "{\"id\": \"value1\", \"password\": \"value2\", \"PhotoString\": \"value2\"}" ^
//   http://localhost:3128/organizers/Pleiades
