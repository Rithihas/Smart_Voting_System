package test.mongo.mongoconnect;

class OrganizerNotFoundException extends RuntimeException {

    OrganizerNotFoundException(String id) {
      super("Could not find organizer " + id);
    }
  }