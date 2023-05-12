package test.mongo.mongoconnect.Voter;


public class Token {

  
  
  
  private String sessionID;

  public Token(){}

  public Token( String sessionID){
    
    this.sessionID = sessionID;

  }





  public String getSessionID()
  {
    return this.sessionID;
  }




  public void setSessionID(String sessionID)
  {
    this.sessionID = sessionID;
  }

  


}

