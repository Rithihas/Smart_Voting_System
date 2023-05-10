package test.mongo.mongoconnect;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @CrossOrigin
    @GetMapping("/voters/generatetoken/{username}")
     String generateToken(@PathVariable String username)
    {
        Voter x = repository.findById(username).get();

        String plainPassword = x.getPassword();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(plainPassword);

        

        return "{\"sessionID\" : "+"\""+encodedPassword+"\""+" }";
       

    }

    @CrossOrigin
    @PostMapping("/voters/validatetoken/{username}")
     String validateToken(@RequestBody Token token,@PathVariable String username)
    {
        

        String plainPassword = repository.findById(username).get().getPassword();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
     

        if(bCryptPasswordEncoder.matches(plainPassword,token.getSessionID()))
        return "{\"authorized\" : \"true\"}";
        else 
        return "{\"authorized\" : \"false\"}";
       

    }


    @CrossOrigin
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

    @CrossOrigin
    @GetMapping("/voters/validateLogin/{username}/{password}")
     String validateLogin(@PathVariable String username , @PathVariable String password)
    {

        System.out.println(username+" "+password);

        if(repository.findById(username).isPresent()) {
            
            Voter x = repository.findById(username).get();

            

            if(x.getPassword().equals(password))
            return "{\"validCredential\" : \"true\"}";
            else 
            return "{\"validCredential\" : \"false\"}";

        }
        else 
        return "{\"validCredential\" : \"false\"}";
    }


    // returns all items
    @CrossOrigin
    @GetMapping("/voters")
    List<Voter> all()
    {
        return repository.findAll();
    }

    @CrossOrigin
    @PostMapping("/voters")
    Voter newVoter(@RequestBody Voter newVoter)
    {
        return repository.save(newVoter);
    }


    //returning single item
    @CrossOrigin
    @GetMapping("/voters/{id}")
    Voter one(@PathVariable String id)
    {
        return repository.findById(id).orElseThrow(()-> new VoterNotFoundException(id));
    }

@CrossOrigin
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

@CrossOrigin
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
