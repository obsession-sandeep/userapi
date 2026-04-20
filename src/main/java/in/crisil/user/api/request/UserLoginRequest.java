package in.crisil.user.api.request;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String mobileNumber;
    private String password;

}
