package model.builder;

import model.entity.User;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserBuilder {
    private Long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Timestamp userCreated;
    private BigDecimal balance;
    private User.UserRole userRole;
    private User.UserStatus userStatus;

    public User build() {
        User user = new User(userId,email,password,firstName,lastName,userCreated,balance,userRole,userStatus);
        this.userId = null;
        this.email = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.userCreated = null;
        this.balance = null;
        this.userRole = null;
        this.userStatus = null;
        return user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Timestamp getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Timestamp userCreated) {
        this.userCreated = userCreated;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User.UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(User.UserRole userRole) {
        this.userRole = userRole;
    }

    public User.UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(User.UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
