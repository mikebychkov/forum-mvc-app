package org.mike.forum.config.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    /*
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        ex.printStackTrace();

        ErrorResponse error = new ErrorResponse(ex);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    */

    @ExceptionHandler
    public String handleException(Exception ex, Model model) {

        ex.printStackTrace();

        model.addAttribute("err", ex.getMessage());

        return "redirect:/oops";
    }

}
