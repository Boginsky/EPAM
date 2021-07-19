package entity;

import util.CashDeskID;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CashDesk {

    private long cashDeskID;
    private CashDeskState state;
    private static final ReentrantLock locker = new ReentrantLock();

    public CashDesk() {
        this.cashDeskID = CashDeskID.generateID();
        this.state = CashDeskState.FREE;
    }

    public long getCashDeskID() {
        return cashDeskID;
    }

    public void setCashDeskID(long cashDeskID) {
        this.cashDeskID = cashDeskID;
    }

    public CashDeskState getState() {
        return state;
    }

    public void setState(CashDeskState state) {
        this.state = state;
    }

    public static List<CashDesk> createCashDesks(int amountOfCashDesks) {
        List<CashDesk> cashDesksList = new ArrayList<>();
        for (int i = 0; i < amountOfCashDesks; i++) {
            CashDesk cashDesk = new CashDesk();
            cashDesksList.add(cashDesk);
        }
        return cashDesksList;
    }

    public void changeState() {
        try {
            locker.lock();
            if (this.getState() == CashDeskState.FREE) {
                this.setState(CashDeskState.BUSY);
            } else {
                this.setState((CashDeskState.FREE));
            }
        } finally {
            locker.unlock();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CashDesk)) return false;
        CashDesk cashDesk = (CashDesk) o;
        return cashDeskID == cashDesk.cashDeskID && state == cashDesk.state;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + ((state == null) ? 0 : state.hashCode());
        return result * 31 + Long.hashCode(cashDeskID);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CashDesk{");
        sb.append("cashDeskID=").append(cashDeskID);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
