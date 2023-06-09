package test.mongo.mongoconnect.Organizer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class OrganizerNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(OrganizerNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(OrganizerNotFoundException ex) {
    return ex.getMessage();
  }
}
