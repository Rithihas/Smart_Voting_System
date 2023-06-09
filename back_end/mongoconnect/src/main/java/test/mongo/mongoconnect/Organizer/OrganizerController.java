package test.mongo.mongoconnect.Organizer;

import java.security.SecureRandom;

import java.util.List;
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
public class OrganizerController {

    @Autowired
	private EmailSenderService  senderService;


    
    private final OrganizerRepository repository;

    private String storedotp;
                                                       
    OrganizerController(OrganizerRepository repository)
    {
        this.repository = repository;
    }

    @CrossOrigin
    @GetMapping("/organizers/requestotp/{email}")
    public String sendMail(@PathVariable String email,Model model){

        Random random = new Random();
        Integer otp = random.nextInt(10000, 99999);

       storedotp = Integer.toString(otp);
        
		senderService.sendEmail(email, "registration OTP", "your OTP for registration is: "+otp);

        return "{\"OtpSent\" : \"true\"}";

      
	}

    @CrossOrigin
    @GetMapping("/organizers/verifyotp/{votp}")
    public String verifyOtp(@PathVariable String votp){

        if(votp.equals(storedotp))
        {
            return "{\"VerifiedOtp\" : \"true\"}";
        }
        else 
        return "{\"VerifiedOtp\" : \"false\"}";

      
	}

    @CrossOrigin
    @GetMapping("/organizers/generatetoken/{username}")
     String generateToken(@PathVariable String username)
    {
        Organizer x = repository.findById(username).get();

        String plainPassword = x.getPassword();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(plainPassword);

        

        return "{\"sessionID\" : "+"\""+encodedPassword+"\""+" }";
       

    }

    @CrossOrigin
    @PostMapping("/organizers/validatetoken/{username}")
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
    @GetMapping("/organizers/verifyuser/{username}")
     String verifyUser(@PathVariable String username)
    {
        if(repository.findById(username).isPresent()) {
         System.out.println(username);
            return "{\"UserPresent\" : \"true\"}";

        }
        else 
        return "{\"UserPresent\" : \"false\"}";
    }

    @CrossOrigin
    @GetMapping("/organizers/validateLogin/{username}/{password}")
     String validateLogin(@PathVariable String username , @PathVariable String password)
    {
      
        if(repository.findById(username).isPresent()) {
            
            Organizer x = repository.findById(username).get();

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
    @GetMapping("/organizers")
    List<Organizer> all()
    {
        return repository.findAll();
    }

    @CrossOrigin
    @PostMapping("/organizers")
    Organizer newOrganizer(@RequestBody Organizer newOrganizer)
    {
        if(repository.findById(newOrganizer.getUsername()).isPresent())
        {
            return null;
        }
        else 
        return repository.save(newOrganizer);
    }


    //returning single item
    @CrossOrigin
    @GetMapping("/organizers/{id}")
    Organizer one(@PathVariable String id)
    {
        return repository.findById(id).orElseThrow(()-> new OrganizerNotFoundException(id));
    }

    @CrossOrigin
    @PutMapping("/organizers/{id}")
    Organizer replaceOrganizer(@RequestBody Organizer newOrganizer , @PathVariable String id)
    {
        return repository.findById(id)
        .map(organizer -> {
          organizer.setUsername(newOrganizer.getUsername());
          organizer.setPassword(newOrganizer.getPassword());
          organizer.setPhotoString(newOrganizer.getPhotoString());
          return repository.save(organizer);
        })
        .orElseGet(() -> {
            
            newOrganizer.setUsername(id);
          return repository.save(newOrganizer);
        });
    }


    @CrossOrigin    
@DeleteMapping("/organizers/{id}")
void deleteOrganizer(@PathVariable String id)
{
    repository.deleteById(id);
}

@CrossOrigin
@GetMapping("/organizers/getdomain/{id}")
String getDomain(@PathVariable String id)
{
    return repository.findById(id).get().getDomainName();
}



}





// curl -X POST ^
//   -H "Content-Type: application/json" ^
//   -d "{\"id\": \"value1\", \"password\": \"value2\", \"PhotoString\": \"value2\"}" ^
//   http://localhost:3128/organizers/Pleiades
