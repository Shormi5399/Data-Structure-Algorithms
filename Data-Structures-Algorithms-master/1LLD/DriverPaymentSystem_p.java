import java.util.*;


public class DriverPaymentSystem{

    public class Driver {
        int id;
        float hourlyRate;
        List<long[]> deliveries;  // [ start and end dates]

        Driver(int id, float hourlyRate){
            this.id = id;
            this.hourlyRate = hourlyRate;
            this.deliveries = new ArrayList<>();
        }
    }

    Private final Map<Integer, Driver> drivers = new HashMap<>();

    public void addDriver(int id, float hourlyRate){
        if(!drivers.containsKey(id)) return;

        drivers.put(id, new Driver(id, hourlyRate));
    }

    public void addDeliveries(int driverId, long startTime, long endTime){
        if(!drivers.contains(id)) return;

        drivers.get(id).deliveries.add(new long[]{startTime, endTime});
    }

    public getTotalAmount(){
        float total = 0f;
        for( Driver driver : drivers.getValue()){

            for(long[] delivery : driver.deliveries){
                total += (delivery[1] - delivery[0])/3600f * driver*hourlyRate;
            }
        }

        return total;
    }
}