package test.mongo.mongoconnect.Organizer;

import java.util.Objects;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Organizers")
public class Organizer {

  @Id
  private String id;
  
  private String password;


  private String PhotoString;

  public Organizer(){}

  public Organizer(String id, String password, String PhotoString){
    
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


  @Override
  public boolean equals(Object o)
   {

    if (this == o)
      return true;
    if (!(o instanceof Organizer))
      return false;
    Organizer organizer = (Organizer) o;
    return Objects.equals(this.id, organizer.id) && Objects.equals(this.password, organizer.password) && Objects.equals(this.PhotoString, organizer.PhotoString);
  }

  public boolean isUsernameSame(Organizer o)
  {
    if(this.id.equals(o.id))
    return true;
    else 
    return false;
  }


}

