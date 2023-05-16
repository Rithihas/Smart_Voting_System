package test.mongo.mongoconnect.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    

    private final EventRepository repository;

    EventController(EventRepository repository)
    {

        this.repository = repository;

    }

    @CrossOrigin
    @GetMapping("/myevents/{domain}")
    List<Event> all(@PathVariable String domain)
    {
        System.out.println(repository.findByOrgDomain(domain));
        return repository.findByOrgDomain(domain);
    }

    @CrossOrigin
    @PostMapping("/events")
    Event newEvent(@RequestBody Event newEvent)
    {
        if(repository.findById(newEvent.getEventname()).isPresent())
        {
            return null;
        }
        else 
        return repository.save(newEvent);
    }

    


    //returning single item
    @CrossOrigin
    @GetMapping("/events/{id}")
    Event one(@PathVariable String id)
    {
        return repository.findById(id).get();
    }

    @CrossOrigin
    @GetMapping("/events/datecheck/{id}")
    String checkDate(@PathVariable String id)
    {
        Event x = repository.findById(id).get();

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd-MM-uuuu");

        LocalDate date = LocalDate.now();
        String text = date.format(f);

        LocalDate date2 = LocalDate.parse(x.getDate(), inputFormatter);
        String formattedDate = date2.format(f);
        

        LocalDate start = LocalDate.parse(text,f);
        LocalDate stop = LocalDate.parse(formattedDate , f );

        Long days = ChronoUnit.DAYS.between( start , stop );

        if(days < 0)
        {
            x.setActiveStatus("false");
            repository.save(x);
        return "inactive";
        }
        else
        {     
        return "active";
        }
    }



    

}
