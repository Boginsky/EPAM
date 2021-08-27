package model.entity.user;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Client extends User {
    private BigDecimal balance;

    public Client(){
    }

    public Client(String email, String firstName, String lastName, Timestamp userCreated, UserRole userRole, UserStatus userStatus ){
        super(email, userRole, userStatus, firstName, lastName, userCreated);
    }
    public Client(Long id, String email, String firstName, String lastName, Timestamp userCreated, UserRole userRole, UserStatus userStatus, BigDecimal balance){
        super(id, email, firstName, lastName, userCreated, userRole, userStatus);
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(balance, client.balance);
    }

    @Override
    public int hashCode() {
        int result = 0;
        return 31 * result + ((balance == null) ? 0 : balance.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}