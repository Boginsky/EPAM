package model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class User extends Entity {

    public enum UserRole {
        ADMIN, USER, VISITOR;
    }

    public enum UserStatus {
        BLOCKED, ACTIVE;
    }

    private Long userId;
    private String email;
    private String password;// FIXME: 24.08.2021 delete
    private String firstName;
    private String lastName;
    private Timestamp userCreated;
    private BigDecimal balance;
    private UserRole userRole;
    private UserStatus userStatus;

    public User(Long userId, String email, String password, String firstName, String lastName, Timestamp userCreated, BigDecimal balance, UserRole userRole, UserStatus userStatus) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userCreated = userCreated;
        this.balance = balance;
        this.userRole = userRole;
        this.userStatus = userStatus;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId.equals(user.userId) && email.equals(user.email) && password.equals(user.password) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && userCreated.equals(user.userCreated) && balance.equals(user.balance) && userRole == user.userRole && userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (31 * result + userId);
        result = 31 * result + ((email == null) ? 0 : email.hashCode());
        result = 31 * result + ((password == null) ? 0 : password.hashCode());
        result = 31 * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = 31 * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = 31 * result + ((userCreated == null) ? 0 : userCreated.hashCode());
        result = 31 * result + ((balance == null) ? 0 : balance.hashCode());
        result = 31 * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = 31 * result + ((userStatus == null) ? 0 : userStatus.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", userCreated=").append(userCreated);
        sb.append(", balance=").append(balance);
        sb.append(", userRole=").append(userRole);
        sb.append(", userStatus=").append(userStatus);
        sb.append('}');
        return sb.toString();
    }

}
