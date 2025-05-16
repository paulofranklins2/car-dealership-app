package persistence;

import contracts.Contract;
import contracts.LeaseContract;
import contracts.SalesContract;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String fileName = "src/main/resources/contracts.txt";
    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            if (contract instanceof SalesContract sale) {
                writer.write(String.format(
                        "SALE|%s|%s|%s|%s|%.2f|%.2f\n",
                        sale.getDate(), sale.getCustomerName(), sale.getCustomerEmail(),
                        sale.getVehicle().getVin(), sale.getTotalPrice(), sale.getMonthlyPayment()
                ));
            } else if (contract instanceof LeaseContract lease) {
                writer.write(String.format(
                        "LEASE|%s|%s|%s|%s|%.2f|%.2f\n",
                        lease.getDate(), lease.getCustomerName(), lease.getCustomerEmail(),
                        lease.getVehicle().getVin(), lease.getTotalPrice(), lease.getMonthlyPayment()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
