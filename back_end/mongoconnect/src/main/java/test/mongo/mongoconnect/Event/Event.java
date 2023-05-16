package test.mongo.mongoconnect.Event;



import java.util.Objects;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Events")
public class Event {

  @Id
  private String id;
  
  private String date;


  private String OrgName;

  private String orgDomain;

  private String activeStatus;

  public Event()
  {}

  public Event(String id, String date, String OrgName,String orgDomain,String activeStatus){
    
    this.id = id;
    this.OrgName = OrgName;
    this.date = date;
    this.orgDomain = orgDomain;
   this.activeStatus = activeStatus;
  
  }
  


  public String getEventname()
  {
    return this.id;
  }


  public String getDate()
  {
    return this.date;
  }

  public String getOrgName()
  {
    return this.OrgName;
  }

  public String getOrgDomain()
  {
    return this.orgDomain;
  }

  public String getActiveStatus()
  {
    return this.activeStatus;
  }

  public void setEventname(String eventname)
  {
    this.id = eventname;
  }


  public void setDate(String date)
  {
    this.date = date;
  }

  public void setOrgName(String OrgName)
  {
    this.OrgName=  OrgName;
  }

  public void setOrgDomain(String orgDomain)
  {
    this.orgDomain = orgDomain;
  }

  public void setActiveStatus(String activeStatus)
  {
    this.activeStatus = activeStatus;
  }



  @Override
  public String toString() 
  {
    return String.format(
        "Event[eventname=%s, duedate='%s', OrgName='%s']",
        id,date,OrgName);
  }


  @Override
  public boolean equals(Object o)
   {

    if (this == o)
      return true;
    if (!(o instanceof Event))
      return false;
    Event Event = (Event) o;
    return Objects.equals(this.id, Event.id) && Objects.equals(this.date, Event.date) && Objects.equals(this.OrgName, Event.OrgName);
  }

  

}

