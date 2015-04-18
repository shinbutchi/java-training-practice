package vn.smartdev.javatrainingpractice.springmvcpractice.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.MatchingPassword;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.ValidEmail;

import javax.validation.constraints.Pattern;

@MatchingPassword
public class UserDTO {

    @NotBlank
    @NotEmpty
    @ValidEmail
    @Pattern(regexp="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String emailAddress;

    @NotBlank
    @NotEmpty
    private String password;

    @NotBlank
    @NotEmpty
    private String retypePassword;

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

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }
}
