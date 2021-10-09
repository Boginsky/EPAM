package by.boginsky.audiostore.model.entity.user;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class User extends AbstractEntity {

    public enum UserRole {
        ADMIN, USER;
    }

    public enum UserStatus {
        BLOCKED, ACTIVE;
    }

    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private BigDecimal balance;
    private BigDecimal bonus;
    private LocalDateTime userCreated;
    private UserRole userRole;
    private UserStatus userStatus;

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

    public LocalDateTime getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(LocalDateTime userCreated) {
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(balance, user.balance) &&
                Objects.equals(bonus, user.bonus) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(imageUrl, user.imageUrl) &&
                Objects.equals(userCreated, user.userCreated) &&
                userRole == user.userRole &&
                userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((email == null) ? 0 : email.hashCode());
        result = 31 * result + ((balance == null) ? 0 : balance.hashCode());
        result = 31 * result + ((bonus == null) ? 0 : bonus.hashCode());
        result = 31 * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = 31 * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = 31 * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
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
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", bonus=").append(bonus);
        sb.append(", userCreated=").append(userCreated);
        sb.append(", userRole=").append(userRole);
        sb.append(", userStatus=").append(userStatus);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder setId(Long id) {
            user.setId(id);
            return this;
        }

        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public Builder setUserCreated(LocalDateTime userCreated) {
            user.setUserCreated(userCreated);
            return this;
        }

        public Builder setUserRole(UserRole userRole) {
            user.setUserRole(userRole);
            return this;
        }

        public Builder setUserStatus(UserStatus userStatus) {
            user.setUserStatus(userStatus);
            return this;
        }

        public Builder setBalance(BigDecimal balance) {
            user.setBalance(balance);
            return this;
        }

        public Builder setBonus(BigDecimal bonus){
            user.setBonus(bonus);
            return this;
        }

        public Builder setImageUrl(String imagUrl){
            user.setImageUrl(imagUrl);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
