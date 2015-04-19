package vn.smartdev.javatrainingpractice.springmvcpractice.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="user")
public class User extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id = UUID.randomUUID().toString();
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String country;
    private String city;
    private String phoneNumber;
    private String username;
    private boolean enabled;
    private boolean accountNonLocked;

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 45)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email_address", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Basic
    @Column(name = "country", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (emailAddress != null ? !emailAddress.equals(user.emailAddress) : user.emailAddress != null) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "username", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "enabled", columnDefinition = "BIT", length = 1, nullable = true, insertable = true, updatable = true)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "account_non_locked", columnDefinition = "BIT", length = 1, nullable = true, insertable = true, updatable = true)
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
}
