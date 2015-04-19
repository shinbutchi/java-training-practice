package vn.smartdev.javatrainingpractice.springmvcpractice.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.MatchingPassword;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.UniqueUsername;
import vn.smartdev.javatrainingpractice.springmvcpractice.validator.ValidEmail;

import javax.validation.constraints.Pattern;

@MatchingPassword
public class UserDTO {
    @NotBlank
    @NotEmpty
    @UniqueUsername
    private String username;

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
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String phoneNumber;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
