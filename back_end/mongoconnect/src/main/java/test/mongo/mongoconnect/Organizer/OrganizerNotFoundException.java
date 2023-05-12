package test.mongo.mongoconnect.Organizer;

class OrganizerNotFoundException extends RuntimeException {

    OrganizerNotFoundException(String id) {
      super("Could not find organizer " + id);
    }
  }