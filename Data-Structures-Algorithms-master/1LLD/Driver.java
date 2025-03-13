import java.util.*;

class Driver {
    String id;
    double hourlyRate;
    List<Long> deliveries; // Stores delivery timestamps

    public Driver(String id, double hourlyRate) {
        this.id = id;
        this.hourlyRate = hourlyRate;
        this.deliveries = new ArrayList<>();
    }

    public void addDelivery(long timestamp) {
        deliveries.add(timestamp);
    }

    public double calculatePayment(long clearTime) {
        int hoursWorked = deliveries.size(); // Assuming 1 delivery = 1 hour worked
        return hoursWorked * hourlyRate;
    }
}

class DriverPaymentSystem {
    private Map<String, Driver> driverMap;
    private long lastClearTime;
    private double totalPaid;

    public DriverPaymentSystem() {
        this.driverMap = new HashMap<>();
        this.lastClearTime = 0; // Initial clear time
        this.totalPaid = 0;
    }

    // Add a driver with their per-hour rate
    public void addDriver(String driverId, double hourlyRate) {
        driverMap.put(driverId, new Driver(driverId, hourlyRate));
    }

    // Add a delivery time for a driver
    public void addDelivery(String driverId, long timestamp) {
        if (driverMap.containsKey(driverId)) {
            driverMap.get(driverId).addDelivery(timestamp);
        }
    }

    // Calculate the total amount owed to all drivers
    public double getTotalPayment() {
        double total = 0;
        for (Driver driver : driverMap.values()) {
            total += driver.calculatePayment(Long.MAX_VALUE);
        }
        return total;
    }

    // Clear payments up to a given timestamp
    public double clearPayments(long clearTime) {
        double clearedAmount = 0;
        for (Driver driver : driverMap.values()) {
            clearedAmount += driver.calculatePayment(clearTime);
            driver.deliveries.removeIf(delivery -> delivery <= clearTime); // Remove cleared deliveries
        }
        lastClearTime = clearTime;
        totalPaid += clearedAmount;
        return clearedAmount;
    }

    // Get total unpaid amount
    public double getUnpaidAmount() {
        return getTotalPayment() - totalPaid;
    }

    public static void main(String[] args) {
        DriverPaymentSystem system = new DriverPaymentSystem();

        // Adding drivers
        system.addDriver("D1", 20.0);
        system.addDriver("D2", 15.0);

        // Adding deliveries
        system.addDelivery("D1", 1700000000L);
        system.addDelivery("D1", 1700003600L);
        system.addDelivery("D2", 1700007200L);

        System.out.println("Total Payment: " + system.getTotalPayment());

        // Clearing payments
        System.out.println("Cleared Amount: " + system.clearPayments(1700003600L));

        // Getting unpaid amount
        System.out.println("Unpaid Amount: " + system.getUnpaidAmount());
    }
}
