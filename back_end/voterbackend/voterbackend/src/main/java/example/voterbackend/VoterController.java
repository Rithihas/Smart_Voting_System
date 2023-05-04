package example.voterbackend;

import java.util.List;

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


    // returns all items
    @GetMapping("/voting")
    List<Voter> all()
    {
        return repository.findAll();
    }

    @PostMapping("/voting")
    Voter newVoter(@RequestBody Voter newVoter)
    {
        return repository.save(newVoter);
    }


    //returning single item
    @GetMapping("/Voting/{id}")
    Voter one(@PathVariable String id)
    {
        return repository.findById(id).orElseThrow(()-> new VoterNotFoundException(id));
    }
//
//    @PutMapping("/organizers/{id}")
//    Organizer replaceOrganizer(@RequestBody Organizer newOrganizer , @PathVariable String id)
//    {
//        return repository.findById(id)
//        .map(organizer -> {
//          organizer.setUsername(newOrganizer.getUsername());
//          organizer.setPassword(newOrganizer.getPassword());
//          organizer.setPhotoString(newOrganizer.getPhotoString());
//          return repository.save(organizer);
//        })
//        .orElseGet(() -> {
//            
//            newOrganizer.setUsername(id);
//          return repository.save(newOrganizer);
//        });
//    }
//
//@DeleteMapping("/organizers/{id}")
//void deleteOrganizer(@PathVariable String id)
//{
//    repository.deleteById(id);
//}
//


}





// curl -X POST ^
//   -H "Content-Type: application/json" ^
//   -d "{\"id\": \"value1\", \"password\": \"value2\", \"PhotoString\": \"value2\"}" ^
//   http://localhost:3128/organizers/Pleiades
