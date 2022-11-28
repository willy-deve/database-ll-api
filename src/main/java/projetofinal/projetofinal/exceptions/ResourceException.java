package projetofinal.projetofinal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandarError> EntityNotFoundException(EntityNotFoundException e,
                                                           HttpServletRequest request)
    {
        StandarError error = new StandarError();
        HttpStatus statusDoErro = HttpStatus.NOT_FOUND; //404
        error.setTimestamp(Instant.now());
        error.setStatus(statusDoErro.value());
        error.setMessage(e.getMessage());
        error.setPatch(request.getRequestURI());
        error.setError("Recurso não encontrado");
        return ResponseEntity.status(statusDoErro).body(error);

    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<StandarError> InternalServerErrorException(InternalServerErrorException e,
                                                            HttpServletRequest request)
    {
        StandarError error = new StandarError();
        HttpStatus statusDoErro = HttpStatus.INTERNAL_SERVER_ERROR; //500
        error.setTimestamp(Instant.now());
        error.setStatus(statusDoErro.value());
        error.setMessage(e.getMessage());
        error.setPatch(request.getRequestURI());
        error.setError("Erro interno no servidor");
        return ResponseEntity.status(statusDoErro).body(error);

    }

    @ExceptionHandler(BadrequestException.class)
    public ResponseEntity<StandarError> BadrequestException(BadrequestException e,
                                                            HttpServletRequest request)
    {
        StandarError error = new StandarError();
        HttpStatus statusDoErro = HttpStatus.BAD_REQUEST; //400
        error.setTimestamp(Instant.now());
        error.setStatus(statusDoErro.value());
        error.setMessage(e.getMessage());
        error.setPatch(request.getRequestURI());
        error.setError("Requisição feita de forma indevida");
        return ResponseEntity.status(statusDoErro).body(error);

    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandarError> databaseException(DatabaseException e,
                                                           HttpServletRequest request)
    {
        StandarError error = new StandarError();
        HttpStatus statusDoErro = HttpStatus.BAD_REQUEST; //400
        error.setTimestamp(Instant.now());
        error.setStatus(statusDoErro.value());
        error.setMessage(e.getMessage());
        error.setPatch(request.getRequestURI());
        error.setError("Banco de dados violado");
        return ResponseEntity.status(statusDoErro).body(error);

    }






}
