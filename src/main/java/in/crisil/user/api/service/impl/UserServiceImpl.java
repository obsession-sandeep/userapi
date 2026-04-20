package in.crisil.user.api.service.impl;

import in.crisil.user.api.model.User;
import in.crisil.user.api.repository.UserRepository;
import in.crisil.user.api.request.UserLoginRequest;
import in.crisil.user.api.request.UserRegistrationRequest;
import in.crisil.user.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public String registerUser(UserRegistrationRequest dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return "Password and Confirm Password do not match!";
        }

        if (userRepository.existsByMobileNumber(dto.getMobileNumber())) {
            return "Mobile number already registered!";
        }

        User user = new User();
        user.setName(dto.getName());
        user.setGender(dto.getGender());
        user.setMobileNumber(dto.getMobileNumber());
        user.setLocation(dto.getLocation());
        user.setPassword(encoder.encode(dto.getPassword())); // In real apps, hash this!
        userRepository.save(user);
        return "User registered successfully!";
    }

    public User login(UserLoginRequest dto) {
        Optional<User> userOpt = userRepository.findByMobileNumber(dto.getMobileNumber());

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }

        User user = userOpt.get();

        if (!encoder.matches(dto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Invalid password!");
        }
        user.setPassword(null);
        return user;
    }
}