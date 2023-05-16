package test.mongo.mongoconnect.Candidate;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Candidates")
public class Candidate {

	  @Id
	  private String id;
	  
	  private String candidateName;
      
      private String eventName;

	  private String PhotoString;

	  private String description;

      private Integer votes;

      public Candidate(String id, String candidateName, String PhotoString, Integer votes, String eventName,String description) {
  		super();
  		this.id = id;
  		this.candidateName = candidateName;
  		this.PhotoString = PhotoString;
  		this.votes = votes;
		this.eventName = eventName;
		this.description = description;
  	}

	@Override
	public String toString() {
		return "Candidate [partyname=" +id+ ", candidateName=" + candidateName + ", PhotoString=" + PhotoString + ", votes=" + votes
				+ "]";
	}
	public String getPartyname()
	  {
	    return this.id;
	  }
	  public void setPartyname(String partyname)
	  {
	    this.id = partyname;
	  }
	public String getCandidateName() {
		return this.candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getPhotoString() {
		return this.PhotoString;
	}
	public void setPhotoString(String PhotoString) {
		this.PhotoString = PhotoString;
	}
	public Integer getVotes() {
		return this.votes;
	}
	public void setVotes(Integer votes) {
		this.votes = votes;
	}


	public String getEventName()
	{
		return this.eventName;
	}

	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	 
	public void setDescription(String description)
	{
		this.description = description;
	}

	 
}
