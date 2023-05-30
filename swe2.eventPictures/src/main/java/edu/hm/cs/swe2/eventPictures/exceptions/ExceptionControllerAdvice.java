package edu.hm.cs.swe2.eventPictures.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * This is a ControllerAdvice class, which handles all the possible exceptions caused by the users input
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * This Exception handles the IllegalArgumentException caused by an illegal argument.
     * For example the user didn't select a picture or a false value in the information fields.
     *
     * @param e thrown Exception
     * @return ResponseEntity with HTTP status and body
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        String message = "Illegal Argument:\n" + e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    /**
     * This exception handles the MissingServletRequestPartException if there is no image posted.
     *
     * @return ResponseEntity with HTTP status and body
     */
    @ExceptionHandler(value = MissingServletRequestPartException.class)
    public ResponseEntity<String> handleMissingServletRequestPartException() {
        String message = "An image is required to upload an Even Picture!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    /**
     * This exception handles the HttpMediaTypeNotSupportedException if there is no value for information and also no image selected.
     *
     * @return ResponseEntity with HTTP status and body
     */
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> handleHttpMediaTypeNotSupportedException() {
        String message = "You have to select at least an image to upload an Event Picture. The image is required!";
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(message);
    }

    /**
     * This exception handles the MultipartException caused by a false filetype or to big file.
     *
     * @param e thrown Exception
     * @return ResponseEntity with HTTP status and body
     */
    @ExceptionHandler(value = MultipartException.class)
    public ResponseEntity<String> handleMultipartException(MultipartException e) {
        String message = "The current request is not a multipart request.\nEither there is no file selected or the file-size is too big (>200MB)!" + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    /**
     * This exception handles the IOException caused by a faulty Input-/Output-operation.
     *
     * @return ResponseEntity with HTTP status and body
     */
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<String> handleIOException() {
        String message = "I/O operation failed...please try again!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    /**
     * This exception is thrown if the given ID is not present in the database.
     *
     * @return ResponseEntity with HTTP status and body
     */
    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResultDataAccessException() {
        String message = "You have to enter a valid ID number. Either the value of the ID number was to high or the database is empty!";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    /**
     * This exception is thrown if the value of the input fields is not allowed because of the Bean Validation in the Entity.
     *
     * @param e thrown Exception
     * @return ResponseEntity with HTTP status and body
     */
    @ExceptionHandler(value = TransactionSystemException.class)
    public ResponseEntity<String> handleTransactionSystemException(TransactionSystemException e) {
        String message = "You made an illegal input: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    /**
     * This exception is thrown if there is no Event Picture in the database.
     *
     * @param e thrown Exception
     * @return ResponseEntity with HTTP status and body
     */
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * This exception is thrown if the format of the Member IDs is not correct.
     *
     * @return ResponseEntity with HTTP status and body
     */
    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException() {
        String message = "The Format for the Member IDs should have '1, 2, 3, 4";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
