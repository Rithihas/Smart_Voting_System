package test.mongo.mongoconnect.Voter;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import test.mongo.mongoconnect.EmailSenderService;


@RestController
public class VoterController {

    @Autowired
	private EmailSenderService  senderService;
    
    private final VoterRepository repository;

    private String storedotp;
                                                       
    VoterController(VoterRepository repository)
    {
        this.repository = repository;
    }

    @CrossOrigin
    @GetMapping("/voters/requestotp/{email}")
    public String sendMail(@PathVariable String email,Model model){

        Random random = new Random();
        Integer otp = random.nextInt(10000, 99999);

        
        
		senderService.sendEmail(email, "registration OTP", "your OTP for registration is: "+otp);

        storedotp = Integer.toString(otp);

        return "{\"OtpSent\" : \"true\"}";

      
	}

    @CrossOrigin
    @GetMapping("/voters/verifyotp/{votp}")
    public String verifyOtp(@PathVariable String votp){

        if(votp.equals(storedotp))
        {
            return "{\"VerifiedOtp\" : \"true\"}";
        }
        else 
        return "{\"VerifiedOtp\" : \"false\"}";

      
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
        
        if(repository.findById(username).isPresent()) {
        String plainPassword = repository.findById(username).get().getPassword();

        System.out.println(token.getSessionID());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
     

        if(bCryptPasswordEncoder.matches(plainPassword,token.getSessionID()))
        return "{\"authorized\" : \"true\"}";
        else 
        return "{\"authorized\" : \"false\"}";
        }
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
    @GetMapping("/votecheck/{id}/{eventName}")
    String voteCheck(@PathVariable String id, @PathVariable String eventName)
    {
        Voter x = repository.findById(id).get();

        List<String> eventlist = x.getEventsVoted();

        if(eventlist.contains(eventName))
        return "{\"votedAlready\" : \"true\"}";
        else 
        return "{\"votedAlready\" : \"false\"}"; 
       
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
   @PutMapping("/addevent/{id}/{eventName}")
   String addEvent(@PathVariable String eventName , @PathVariable String id)
   {
       Optional<Voter> optionalVoter = repository.findById(id);

       if(optionalVoter.isPresent())
       {
        Voter x = optionalVoter.get();
        List<String> events = x.getEventsVoted();
        events.add(eventName);
        x.setEventsVoted(events);
        repository.save(x);
        return "{\"eventVoted\" : \"true\"}"; 
       }
       else 
       {
        return "{\"eventVoted\" : \"false\"}";
       }
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
