package test.mongo.mongoconnect;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoconnectApplication  {

	// @Autowired
  // private OrganizerRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(MongoconnectApplication.class, args);
	}

	// @Override
  // public void run(String... args) throws Exception {

  //   // repository.deleteAll();

  //   // save a couple of Organizers
  //   // repository.save(new Organizer("Trial", "123","abc"));
  //   // repository.save(new Organizer("Trial2", "321","xyz"));

  //   // // fetch all Organizers
  //   // System.out.println("Organizers found with findAll():");
  //   // System.out.println("-------------------------------");
  //   // for (Organizer Organizer : repository.findAll()) {
  //   //   System.out.println(Organizer);
  //   // }
  //   // System.out.println();

  //   // fetch an individual Organizer
  //   System.out.println("Organizer found with findByUserName('Pleiades'):");
  //   System.out.println("--------------------------------");
  //   System.out.println(repository.findByUsername("Pleiades"));

  

  // }

}
