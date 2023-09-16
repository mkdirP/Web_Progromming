package application.service;

import application.domain.User;
import application.repository.UserRepository;
import application.util.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final String pepper = "4Hv@*!1rdSQ7";
    private static final Random RANDOM = new SecureRandom();

    @Transactional
    public User saveUser(String login, String password) {
        byte[] salt = new byte[6];
        RANDOM.nextBytes(salt);
        String saltString = new String(salt, StandardCharsets.UTF_8);
        User user = new User(login, PasswordHasher.encryptStringSHA(pepper + password + saltString), saltString);
        return userRepository.save(user);
    }

    @Transactional
    public User findByLogin(String login) {
        return userRepository.getByUsername(login);
    }

    @Transactional
    public User findByLoginAndPassword(String login, String password) {
        User user = userRepository.getByUsername(login);
        if (user != null) {
            String saltString = user.getSalt();
            String toBeCheckedPassword = PasswordHasher.encryptStringSHA(pepper + password + saltString);
            return userRepository.getByUsernameAndPassword(login, toBeCheckedPassword);
        }
        return null;
    }
}
