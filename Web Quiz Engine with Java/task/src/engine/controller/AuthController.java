package engine.controller;

import engine.model.Author;
import engine.model.dto.request.RegisterRequest;
import engine.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        Author author = authService.register(request);

        if (null == author) {
            return ResponseEntity.badRequest().body("Registration failed");
        }

        return ResponseEntity.ok("Registration successful");
    }
}
