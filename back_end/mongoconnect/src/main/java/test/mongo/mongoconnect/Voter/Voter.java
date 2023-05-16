package test.mongo.mongoconnect.Voter;



import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Voters")
public class Voter {
	@Id
	  private String id;
	  
	  private String password;


	  private String PhotoString;

	  private List<String> eventsVoted;

	  public Voter(){}

	  public Voter(String id, String password, String PhotoString){
	    
	    this.id = id;
	    this.password = password;

	    this.PhotoString = PhotoString;

		this.eventsVoted = new ArrayList<String>();

	  }


	  public String getUsername()
	  {
	    return this.id;
	  }


	  public String getPassword()
	  {
	    return this.password;
	  }

	  public String getPhotoString()
	  {
	    return this.PhotoString;
	  }

	  public List<String> getEventsVoted()
	  {
		return this.eventsVoted;
	  }

	  public void setUsername(String username)
	  {
	    this.id = username;
	  }


	  public void setPassword(String password)
	  {
	    this.password = password;
	  }

	  public void setPhotoString(String PhotoString)
	  {
	    this.PhotoString = PhotoString;
	  }

	  public void setEventsVoted(List<String> eventsVoted)
	  {
		this.eventsVoted = eventsVoted;
	  }




	  @Override
	  public String toString() 
	  {
	    return String.format(
	        "Organizer[username=%s, password='%s', PhotoString='%s']",
	        id,password,PhotoString);
	  }


	 

	 


	

}
