package in.crisil.user.api.request;
import lombok.Data;

@Data
public class UserRegistrationRequest {

        private String name;
        private String gender;
        private String mobileNumber;
        private String location;
        private String password;
        private String confirmPassword;
    }

