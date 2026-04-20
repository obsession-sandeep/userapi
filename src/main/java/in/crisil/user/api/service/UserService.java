package in.crisil.user.api.service;

import in.crisil.user.api.model.User;
import in.crisil.user.api.request.UserLoginRequest;
import in.crisil.user.api.request.UserRegistrationRequest;

public interface UserService {
    String registerUser(UserRegistrationRequest dto);
    User login(UserLoginRequest request);
}
