package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CustomerID;

import java.util.concurrent.Callable;

public class Customers implements Callable<Customers> {

    private static final Logger logger = LogManager.getLogger();

    private long customerID;
    private CustomerType type;

    public Customers() {
        this.customerID = CustomerID.generateID();
        this.type = (int) (Math.random() * 2) == 1 ? CustomerType.NOT_PRE_ORDERED : CustomerType.PRE_ORDERED;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    @Override
    public Customers call() {
        McDonalds mcDonalds = McDonalds.getInstance();
        mcDonalds.addCustomer(this);
        mcDonalds.makeOrder(this);
        mcDonalds.removeCustomer(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customers)) return false;
        Customers customers = (Customers) o;
        return customerID == customers.customerID && type == customers.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Long.hashCode(customerID);
        return result * 31 + ((type == null) ? 0 : type.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customers{");
        sb.append("customerID=").append(customerID);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
