package test.mongo.mongoconnect;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Voters")
public class Voter {
	@Id
	  private String id;
	  
	  private String password;


	  private String PhotoString;

	  public Voter(){}

	  public Voter(String id, String password, String PhotoString){
	    
	    this.id = id;
	    this.password = password;

	    this.PhotoString = PhotoString;

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




	  @Override
	  public String toString() 
	  {
	    return String.format(
	        "Organizer[username=%s, password='%s', PhotoString='%s']",
	        id,password,PhotoString);
	  }


	 

	 


	

}
