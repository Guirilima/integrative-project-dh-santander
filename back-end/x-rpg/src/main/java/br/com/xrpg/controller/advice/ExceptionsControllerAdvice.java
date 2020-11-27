package br.com.xrpg.controller.advice;

import br.com.xrpg.vo.HttpGenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<HttpGenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        //return ex.getBindingResult().getFieldError().getField() + " - " + ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                .status("NOK")
                .mensagem( ex.getBindingResult().getFieldError().getField() + " - " +
                        ex.getBindingResult().getFieldError().getDefaultMessage() )
                .response( null ).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(Exception ex) {
        return "Internal Server Error: " + ex.getMessage();
    }
}
