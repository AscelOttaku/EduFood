package kg.attractor.edufood.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.edufood.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ErrorServiceImpl implements ErrorService {

    @Override
    public Map<String, Object> handleMethodValidationException(
            HandlerMethodValidationException ex, HttpServletRequest request
    ) {
        Map<String, Object> errors = new HashMap<>();

        ex.getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            Object arguments = error.getArguments();

            errors.put("timestamp", DateTimeFormatter
                    .ofPattern("dd:MM:yyyy HH:mm:ss")
                    .format(LocalDateTime.now()));

            errors.put("message", errorMessage);
            errors.put("arguments", arguments);
            errors.put("path", request.getRequestURI());
        });

        return errors;
    }
}
