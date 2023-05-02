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
public class OrganizerController {
    
    private final OrganizerRepository repository;
                                                       
    OrganizerController(OrganizerRepository repository)
    {
        this.repository = repository;
    }


    // returns all items
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/organizers")
    List<Organizer> all()
    {
        return repository.findAll();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
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
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/organizers/{id}")
    Organizer one(@PathVariable String id)
    {
        return repository.findById(id).orElseThrow(()-> new OrganizerNotFoundException(id));
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
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


    @CrossOrigin(origins = "http://127.0.0.1:5500/")    
@DeleteMapping("/organizers/{id}")
void deleteOrganizer(@PathVariable String id)
{
    repository.deleteById(id);
}



}





// curl -X POST ^
//   -H "Content-Type: application/json" ^
//   -d "{\"id\": \"value1\", \"password\": \"value2\", \"PhotoString\": \"value2\"}" ^
//   http://localhost:3128/organizers/Pleiades
