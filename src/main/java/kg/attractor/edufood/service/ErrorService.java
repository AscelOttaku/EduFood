package kg.attractor.edufood.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Map;

public interface ErrorService {
    Map<String, Object> handleMethodValidationException(
            HandlerMethodValidationException ex, HttpServletRequest request
    );
}
