import java.math.BigDecimal;
import java.util.*;

class DriverPaymentSystem {
    static class Driver {
        int id;
        BigDecimal hourlyRate;
        List<long[]> deliveries; // Stores (startTime, endTime) for each delivery

        Driver(int id, BigDecimal hourlyRate) {
            this.id = id;
            this.hourlyRate = hourlyRate;
            this.deliveries = new ArrayList<>();
        }
    }

    private final Map<Integer, Driver> drivers = new HashMap<>();
    private final Map<Integer, Long> lastPaymentTime = new HashMap<>();

    // Adds a driver with an hourly rate
    public void addDriver(int id, BigDecimal hourlyRate) {
        drivers.put(id, new Driver(id, hourlyRate));
        lastPaymentTime.put(id, 0L);
    }

    // Adds a delivery with start and end time
    public void addDelivery(int driverId, long startTime, long endTime) {
        if (!drivers.containsKey(driverId)) {
            System.out.println("Error: Driver ID " + driverId + " does not exist.");
            return;
        }
        drivers.get(driverId).deliveries.add(new long[]{startTime, endTime});
    }

    // Computes total payment based on worked hours
    public BigDecimal getTotalPayment() {
        BigDecimal total = BigDecimal.ZERO;
        for (Driver driver : drivers.values()) {
            for (long[] delivery : driver.deliveries) {
                long duration = delivery[1] - delivery[0]; // Compute work duration
                BigDecimal hoursWorked = BigDecimal.valueOf(duration).divide(BigDecimal.valueOf(3600), 2, BigDecimal.ROUND_HALF_UP);
                total = total.add(hoursWorked.multiply(driver.hourlyRate));
            }
        }
        return total;
    }

    // Computes payment till a given time
    public BigDecimal getPaymentTill(long epochTime) {
        BigDecimal total = BigDecimal.ZERO;
        for (Driver driver : drivers.values()) {
            for (long[] delivery : driver.deliveries) {
                if (delivery[1] <= epochTime) { // Consider completed deliveries only
                    long duration = delivery[1] - delivery[0];
                    BigDecimal hoursWorked = BigDecimal.valueOf(duration).divide(BigDecimal.valueOf(3600), 2, BigDecimal.ROUND_HALF_UP);
                    total = total.add(hoursWorked.multiply(driver.hourlyRate));
                }
            }
        }
        return total;
    }

    // Computes unpaid amount after a given time
    public BigDecimal getUnpaidAmount(long epochTime) {
        BigDecimal total = BigDecimal.ZERO;
        for (Driver driver : drivers.values()) {
            for (long[] delivery : driver.deliveries) {
                if (delivery[1] > epochTime) { // Consider pending deliveries
                    long duration = delivery[1] - delivery[0];
                    BigDecimal hoursWorked = BigDecimal.valueOf(duration).divide(BigDecimal.valueOf(3600), 2, BigDecimal.ROUND_HALF_UP);
                    total = total.add(hoursWorked.multiply(driver.hourlyRate));
                }
            }
        }
        return total;
    }

    // Updates hourly rate for a driver
    public void updateHourlyRate(int driverId, BigDecimal newRate) {
        if (drivers.containsKey(driverId)) {
            drivers.get(driverId).hourlyRate = newRate;
        } else {
            System.out.println("Error: Driver ID " + driverId + " does not exist.");
        }
    }

    public static void main(String[] args) {
        DriverPaymentSystem dps = new DriverPaymentSystem();
        dps.addDriver(1, new BigDecimal("20.50"));
        dps.addDriver(2, new BigDecimal("25.75"));

        dps.addDelivery(1, 1700000000, 1700003600);
        dps.addDelivery(1, 1700003600, 1700007200);
        dps.addDelivery(2, 1700007200, 1700010800);

        System.out.println("Total Payment: " + dps.getTotalPayment());
        System.out.println("Payment till 1700003600: " + dps.getPaymentTill(1700003600));
        System.out.println("Unpaid amount after 1700003600: " + dps.getUnpaidAmount(1700003600));
    }
}
