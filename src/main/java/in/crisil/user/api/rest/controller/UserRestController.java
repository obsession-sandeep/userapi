package in.crisil.user.api.rest.controller;

import in.crisil.user.api.model.User;
import in.crisil.user.api.request.UserLoginRequest;
import in.crisil.user.api.request.UserRegistrationRequest;
import in.crisil.user.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRegistrationRequest dto) {
        return userService.registerUser(dto);
    }
    @PostMapping("/login")
    public User login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

}
