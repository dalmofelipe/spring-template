package com.dalmofelipe.springtemplate.exceptions;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dalmofelipe.springtemplate.exceptions.contracts.MessageException;
import com.dalmofelipe.springtemplate.exceptions.dtos.ApiErrorDTO;
import com.dalmofelipe.springtemplate.exceptions.dtos.ErrorDTO;

import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("null")
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final String UNKNOWN_ERROR_KEY = "unknown.error";
    private static ch.qos.logback.classic.Logger logger;
    private final MessageSource messageSource;

    {
        LoggerContext context = new LoggerContext();
        logger = context.getLogger("testLogger");
    }


    public ExceptionHandlerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        ((org.slf4j.Logger) logger)
            .error("Exception {}, Message: {}", exception.getClass().getName(), exception.getMessage());
            
        Set<ErrorDTO> errors = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> buildError(error.getCode(), error.getDefaultMessage()))
            .collect(Collectors.toSet());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
    }

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ApiErrorDTO> handlerBaseException(Throwable exception) {
        ((org.slf4j.Logger) logger)
            .error("Exception {}", exception.getClass().getName());

        MessageException messageException = (MessageException) exception;
        ErrorDTO error = buildError(messageException.getExceptionKey(),
            bindExceptionKeywords(messageException.getMapDetails(),messageException.getExceptionKey()));
        Set<ErrorDTO> errors = Set.of(error);
        ApiErrorDTO apiErrorDto = baseErrorBuilder(getResponseStatus(exception), errors);

        return ResponseEntity
            .status(getResponseStatus(exception))
            .body(apiErrorDto);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorDTO> handlerMethodThrowable(Throwable exception) {
        ((org.slf4j.Logger) logger)
            .error("Exception {}, Message: {}", exception.getClass().getName(), exception.getMessage());

        Set<ErrorDTO> errors = Set.of(buildError(UNKNOWN_ERROR_KEY, exception.getMessage()));

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(baseErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errors));
    }

    private ErrorDTO buildError(String code, String message) {
        return new ErrorDTO(code, message);
    }

    private ApiErrorDTO baseErrorBuilder(HttpStatus httpStatus, Set<ErrorDTO> errorList) {
        return new ApiErrorDTO(LocalDateTime.now(), httpStatus.value(), httpStatus.name(), errorList);
    }

    private String bindExceptionKeywords(Map<String, Object> keywords, String exceptionKey) {
        String message = messageSource.getMessage(exceptionKey, null, LocaleContextHolder.getLocale());
        return Objects.nonNull(keywords) ? new StringSubstitutor(keywords).replace(message) : message;
    }

    private HttpStatus getResponseStatus(Throwable exception) {
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);

        if (exception.getClass().getAnnotation(ResponseStatus.class) == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return responseStatus.value();
    }
}