package test.mongo.mongoconnect.Voter;

class VoterNotFoundException extends RuntimeException {

    VoterNotFoundException(String id) {
      super("Could not find the voter " + id);
    }
  }