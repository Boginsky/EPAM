package model.entity.user;

import model.entity.AbstractEntity;

import java.sql.Timestamp;
import java.util.Objects;

public class User extends AbstractEntity {

    public enum UserRole {
        ADMIN, CLIENT, VISITOR;
    }

    public enum UserStatus {
        BLOCKED, ACTIVE;
    }

    private String email;
    private String firstName;
    private String lastName;
    private Timestamp userCreated;
    private UserRole userRole;
    private UserStatus userStatus;

    public User(){
        super();
    }

    public User(Long id, String email){
        super(id);
        this.email = email;
    }

    public User(String email, UserRole userRole, UserStatus userStatus, String firstName, String lastName, Timestamp userCreated){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userCreated = userCreated;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public User(Long id, String email, String firstName, String lastName, Timestamp userCreated, UserRole userRole, UserStatus userStatus) {
        super(id);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userCreated = userCreated;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userCreated, user.userCreated) &&
                userRole == user.userRole &&
                userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((email == null) ? 0 : email.hashCode());
        result = 31 * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = 31 * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = 31 * result + ((userCreated == null) ? 0 : userCreated.hashCode());
        result = 31 * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = 31 * result + ((userStatus == null) ? 0 : userStatus.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", userCreated=").append(userCreated);
        sb.append(", userRole=").append(userRole);
        sb.append(", userStatus=").append(userStatus);
        sb.append('}');
        return sb.toString();
    }
}
