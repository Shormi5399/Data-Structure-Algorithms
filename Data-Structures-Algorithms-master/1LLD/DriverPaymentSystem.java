/*
Implement 3 APIs for a delivery system app. The delivery driver has an hourly rate for performing delivery. Implement following three APIs:
addDriver(driverId(integer), hourlyRate(float))
addOrder(driverId, startTime, endTime): mention the choice of datatype you choose for time and why
getTotalCost for all the deliveries.
*/
/////////////////////////////////////////////

// just get total cost

import java.util.*;

class DriverPaymentSystem {
    static class Driver {
        int id;
        float hourlyRate;
        List<long[]> deliveries;
        float balance;

        Driver(int id, float hourlyRate) {
            this.id = id;
            this.hourlyRate = hourlyRate;
            this.deliveries = new ArrayList<>();
        }
    }

    private final Map<Integer, Driver> drivers = new HashMap<>();

    public void addDriver(int id, float hourlyRate) {
        drivers.put(id, new Driver(id, hourlyRate));
    }

    public void addDelivery(int driverId, long startTime, long endTime) {
        if (!drivers.containsKey(driverId) || endTime <= startTime) return;
        drivers.get(driverId).deliveries.add(new long[]{startTime, endTime});
        drivers.get(driverId).balance.
    }

    public float getTotalPayment() {
        float total = 0f;
        for (Driver driver : drivers.values()) {
            for (long[] delivery : driver.deliveries) {
                total += ((delivery[1] - delivery[0]) / 3600f) * driver.hourlyRate;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        DriverPaymentSystem dps = new DriverPaymentSystem();
        dps.addDriver(1, 20.50f);
        dps.addDriver(2, 25.75f);

        dps.addDelivery(1, 1700000000, 1700003600);
        dps.addDelivery(1, 1700003600, 1700007200);
        dps.addDelivery(2, 1700007200, 1700010800);

        System.out.println("Total Payment: " + dps.getTotalPayment());
    }
}


//////////////////////////////////////////////

// get unpaid amount

 java.math.BigDecimal;
import java.util.*;

class DriverPaymentSystem {
    static class Driver {
        int id;
        float hourlyRate;
        List<long[]> deliveries; // Stores (startTime, endTime) for each delivery

        Driver(int id, float hourlyRate) {
            this.id = id;
            this.hourlyRate = hourlyRate;
            this.deliveries = new ArrayList<>();
        }
    }

    private final Map<Integer, Driver> drivers = new HashMap<>();
    private final Map<Integer, Long> lastPaymentTime = new HashMap<>();

    /**
     * Adds a driver with an hourly rate.
     */
    public void addDriver(int id, float hourlyRate) {
        if (drivers.containsKey(id)) {
            System.out.println("Error: Driver ID " + id + " already exists.");
            return;
        }
        drivers.put(id, new Driver(id, hourlyRate));
        lastPaymentTime.put(id, 0L);
    }

    /**
     * Adds a delivery with start and end time.
     */
    public void addDelivery(int driverId, long startTime, long endTime) {
        if (!drivers.containsKey(driverId)) {
            System.out.println("Error: Driver ID " + driverId + " does not exist.");
            return;
        }
        if (endTime <= startTime) {
            System.out.println("Error: Invalid delivery time for driver " + driverId);
            return;
        }
        drivers.get(driverId).deliveries.add(new long[]{startTime, endTime});
    }

    /**
     * Computes total payment based on worked hours for all drivers.
     */
    public float getTotalPayment() {
        float total = 0f;
        for (Driver driver : drivers.values()) {
            total += calculatePayment(driver.deliveries, driver.hourlyRate, Long.MAX_VALUE);
        }
        return total;
    }

    /**
     * Computes payment till a given time.
     */
    public float getPaymentTill(long epochTime) {
        float total = 0f;
        for (Driver driver : drivers.values()) {
            total += calculatePayment(driver.deliveries, driver.hourlyRate, epochTime);
        }
        return total;
    }

    /**
     * Computes unpaid amount after a given time.
     */
    public float getUnpaidAmount(long epochTime) {
        float total = 0f;
        for (Driver driver : drivers.values()) {
            for (long[] delivery : driver.deliveries) {
                if (delivery[1] > epochTime) { // Consider pending deliveries
                    long duration = delivery[1] - Math.max(delivery[0], epochTime);
                    float hoursWorked = (float) duration / 3600;
                    total += hoursWorked * driver.hourlyRate;
                }
            }
        }
        return total;
    }

    /**
     * Updates hourly rate for a driver.
     */
    public void updateHourlyRate(int driverId, float newRate) {
        if (!drivers.containsKey(driverId)) {
            System.out.println("Error: Driver ID " + driverId + " does not exist.");
            return;
        }
        drivers.get(driverId).hourlyRate = newRate;
    }

    /**
     * Helper function to compute total payment up to a given time.
     */
    private float calculatePayment(List<long[]> deliveries, float hourlyRate, long maxTime) {
        float total = 0f;
        for (long[] delivery : deliveries) {
            if (delivery[1] <= maxTime) { // Consider completed deliveries only
                long duration = delivery[1] - delivery[0];
                float hoursWorked = (float) duration / 3600;
                total += hoursWorked * hourlyRate;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        DriverPaymentSystem dps = new DriverPaymentSystem();
        dps.addDriver(1, 20.50f);
        dps.addDriver(2, 25.75f);

        dps.addDelivery(1, 1700000000, 1700003600);
        dps.addDelivery(1, 1700003600, 1700007200);
        dps.addDelivery(2, 1700007200, 1700010800);

        System.out.println("Total Payment: " + dps.getTotalPayment());
        System.out.println("Payment till 1700003600: " + dps.getPaymentTill(1700003600));
        System.out.println("Unpaid amount after 1700003600: " + dps.getUnpaidAmount(1700003600));
    }
}




// Follow ups:
// what is the issue with using float and double as data type for currency and what data type should you use?
// what changes would you make in the code if we want to update the hourly rate of a driver.

// float and double cannot precise store decimal - 0.1
// inconistance comparision - ! may 0.3+ 0.6  == 0.9
// we can use BigDecimal
