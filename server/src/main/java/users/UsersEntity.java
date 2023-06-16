package users;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users", schema = "s371623", catalog = "studs")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "username", nullable = false, length = 255)
    private String username;
    @Basic
    @Column(name = "passwordhash", nullable = true, length = 255)
    private String passwordhash;
    @Basic
    @Column(name = "passwordsalt", nullable = true, length = 255)
    private String passwordsalt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public String getPasswordsalt() {
        return passwordsalt;
    }

    public void setPasswordsalt(String passwordsalt) {
        this.passwordsalt = passwordsalt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(username, that.username) && Objects.equals(passwordhash, that.passwordhash) && Objects.equals(passwordsalt, that.passwordsalt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwordhash, passwordsalt);
    }
}
