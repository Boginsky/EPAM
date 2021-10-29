package by.boginsky.audiostore.model.entity.user;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type User.
 */
public class User extends AbstractEntity {

    /**
     * The enum User role.
     */
    public enum UserRole {
        /**
         * Admin user role.
         */
        ADMIN,
        /**
         * User user role.
         */
        USER
    }

    /**
     * The enum User status.
     */
    public enum UserStatus {
        /**
         * Blocked user status.
         */
        BLOCKED,
        /**
         * Active user status.
         */
        ACTIVE
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

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets user created.
     *
     * @return the user created
     */
    public LocalDateTime getUserCreated() {
        return userCreated;
    }

    /**
     * Sets user created.
     *
     * @param userCreated the user created
     */
    public void setUserCreated(LocalDateTime userCreated) {
        this.userCreated = userCreated;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Gets user status.
     *
     * @return the user status
     */
    public UserStatus getUserStatus() {
        return userStatus;
    }

    /**
     * Sets user status.
     *
     * @param userStatus the user status
     */
    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets bonus.
     *
     * @return the bonus
     */
    public BigDecimal getBonus() {
        return bonus;
    }

    /**
     * Sets bonus.
     *
     * @param bonus the bonus
     */
    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets image url.
     *
     * @param imageUrl the image url
     */
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

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private final User user;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            user = new User();
        }

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public Builder setId(Long id) {
            user.setId(id);
            return this;
        }

        /**
         * Sets email.
         *
         * @param email the email
         * @return the email
         */
        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        /**
         * Sets first name.
         *
         * @param firstName the first name
         * @return the first name
         */
        public Builder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        /**
         * Sets last name.
         *
         * @param lastName the last name
         * @return the last name
         */
        public Builder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        /**
         * Sets user created.
         *
         * @param userCreated the user created
         * @return the user created
         */
        public Builder setUserCreated(LocalDateTime userCreated) {
            user.setUserCreated(userCreated);
            return this;
        }

        /**
         * Sets user role.
         *
         * @param userRole the user role
         * @return the user role
         */
        public Builder setUserRole(UserRole userRole) {
            user.setUserRole(userRole);
            return this;
        }

        /**
         * Sets user status.
         *
         * @param userStatus the user status
         * @return the user status
         */
        public Builder setUserStatus(UserStatus userStatus) {
            user.setUserStatus(userStatus);
            return this;
        }

        /**
         * Sets balance.
         *
         * @param balance the balance
         * @return the balance
         */
        public Builder setBalance(BigDecimal balance) {
            user.setBalance(balance);
            return this;
        }

        /**
         * Sets bonus.
         *
         * @param bonus the bonus
         * @return the bonus
         */
        public Builder setBonus(BigDecimal bonus) {
            user.setBonus(bonus);
            return this;
        }

        /**
         * Sets image url.
         *
         * @param imagUrl the imag url
         * @return the image url
         */
        public Builder setImageUrl(String imagUrl) {
            user.setImageUrl(imagUrl);
            return this;
        }

        /**
         * Build user.
         *
         * @return the user
         */
        public User build() {
            return user;
        }
    }
}
