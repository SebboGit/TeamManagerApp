package edu.hm.cs.swe2.calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlers {

  @ExceptionHandler(value = IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Arrays.toString(ex.getStackTrace()));
  }

  @ExceptionHandler(value = NullPointerException.class)
  public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Arrays.toString(ex.getStackTrace()));
  }

  @ExceptionHandler(value = NoSuchElementException.class)
  public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Arrays.toString(ex.getStackTrace()));
  }
}
