package creator;

import entity.Customers;

import java.util.ArrayList;
import java.util.List;

public class CustomersCreator {

    public static List<Customers> createCustomers(List<Integer> characteristicsForWork) {
        List finalCustomersList = new ArrayList();
        for (int j = 0; j < characteristicsForWork.size(); j++) {
            List<Customers> localCustomersList = new ArrayList<>();
            for (int i = 0; i < characteristicsForWork.get(j); i++) {
                Customers customer = new Customers();
                localCustomersList.add(customer);
            }
            finalCustomersList.add(localCustomersList);
        }
        return finalCustomersList;
    }
}
