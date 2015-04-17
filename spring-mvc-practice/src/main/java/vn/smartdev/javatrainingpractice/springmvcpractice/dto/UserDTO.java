package vn.smartdev.javatrainingpractice.springmvcpractice.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.ValidEmail;

public class UserDTO {

//    @NotBlank
//    @NotEmpty
//    @Email
    @ValidEmail
    private String emailAddress;

    @NotBlank
    @NotEmpty
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
