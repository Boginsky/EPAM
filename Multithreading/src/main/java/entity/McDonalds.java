package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class McDonalds {

    private static final Logger logger = LogManager.getLogger();
    private static McDonalds instance;
    private static final int AMOUNT_OF_CASH_DESKS = 5;
    private static final ReentrantLock locker = new ReentrantLock();
    private static final AtomicBoolean create = new AtomicBoolean(false);

    private List<CashDesk> cashDeskList = CashDesk.createCashDesks(AMOUNT_OF_CASH_DESKS);

    public McDonalds() {
    }

    public static McDonalds getInstance() {
        if (!create.get()) {
            try {
                locker.lock();
                if (instance == null) {
                    instance = new McDonalds();
                    create.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }


    public void addCustomer(Customers customer) {
        try {
            locker.lock();
            logger.info("Customer with ID: " + customer.getCustomerID() + " entered the building");
        } finally {
            locker.unlock();
        }
    }

    public void removeCustomer(Customers customer) {
        try {
            locker.lock();
            logger.info("Customer with ID: " + customer.getCustomerID() + " has left the building");
        } finally {
            locker.unlock();
        }
    }

    public void makeOrder(Customers customer) {
        try {
            if (customer.getType() == CustomerType.PRE_ORDERED) {
                logger.info("Customer with ID: " + customer.getCustomerID() + " pre-ordered");
                logger.info("Customer with ID: " + customer.getCustomerID() + " received his order");
            } else {
                CashDesk cashDesk = cashDeskList.get((int) (Math.random() * 5));
                if (cashDesk.getState() == CashDeskState.FREE) {
                    cashDesk.changeState();
                    logger.info("Customer with ID: " + customer.getCustomerID() + " was added to the queue to the cash desk with ID " + cashDesk.getCashDeskID());
                    TimeUnit.MILLISECONDS.sleep(2000);
                    logger.info("Customer with ID: " + customer.getCustomerID() + " received his order");
                    cashDesk.changeState();
                } else {
                    logger.info("Customer with ID: " + customer.getCustomerID() + " has to wait");
                    TimeUnit.MILLISECONDS.sleep(3000);
                    makeOrder(customer);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof McDonalds)) return false;
        McDonalds mcDonalds = (McDonalds) o;
        return cashDeskList.equals(mcDonalds.cashDeskList);
    }

    @Override
    public int hashCode() {
        int result = 1;
        return result * 31 + cashDeskList.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("McDonalds{");
        sb.append("cashDeskList=").append(cashDeskList);
        sb.append('}');
        return sb.toString();
    }
}







