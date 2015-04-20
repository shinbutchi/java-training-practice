package vn.smartdev.javatrainingpractice.springmvcpractice.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_attempts", schema = "", catalog = "java_training")
public class UserAttempts {
    private int id;
    private String username;
    private Integer attempts;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, insertable = true, updatable = true, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "attempts", nullable = true, insertable = true, updatable = true)
    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAttempts that = (UserAttempts) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (attempts != null ? !attempts.equals(that.attempts) : that.attempts != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (attempts != null ? attempts.hashCode() : 0);
        return result;
    }
}
