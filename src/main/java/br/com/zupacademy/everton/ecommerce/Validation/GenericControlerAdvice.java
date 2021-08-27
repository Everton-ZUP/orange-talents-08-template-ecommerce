package br.com.zupacademy.everton.ecommerce.Validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GenericControlerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ReturnError validacao (MethodArgumentNotValidException exception){

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        ReturnError erros = new ReturnError();

        globalErrors.forEach(erro -> erros.AddError(erro.getDefaultMessage()));
        fieldErrors.forEach(erro -> erros.addErrorField(erro.getField(),erro.getRejectedValue(),erro.getDefaultMessage()));

        return erros;
    }

}
