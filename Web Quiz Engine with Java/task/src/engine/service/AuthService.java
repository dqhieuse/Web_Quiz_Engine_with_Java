package engine.service;

import engine.exception.BadRequestException;
import engine.model.Author;
import engine.model.dto.AuthorAdapter;
import engine.model.dto.request.RegisterRequest;
import engine.repository.AuthorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    AuthService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Author author = authorRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " not found")
        );

        return new AuthorAdapter(author);
    }

    public Author register(RegisterRequest request) {
        if (authorRepository.existsAuthorByEmail(request.email())) {
            throw new BadRequestException("User with email " + request.email() + " already exists");
        }

        Author author = new Author();
        author.setEmail(request.email());
        author.setPassword(passwordEncoder.encode(request.password()));
        return authorRepository.save(author);
    }
}
