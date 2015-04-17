package vn.smartdev.javatrainingpractice.springmvcpractice.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.ValidEmail;

import javax.validation.constraints.Pattern;

public class UserDTO {

    @NotBlank
    @NotEmpty
    @ValidEmail
    @Pattern(regexp="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
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
