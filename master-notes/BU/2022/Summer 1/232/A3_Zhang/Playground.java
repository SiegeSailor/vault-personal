import java.io.*;
import java.util.*;

import source.vehicle.*;

public class Playground {
    public static void wrapCatch(String description, Exception event) {
        System.out.println(description);
        System.out.println(event.getMessage());
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Do you want to print tack trace? (Y/N)");
        String input = keyboard.nextLine();
        if (input.equals("Y")) {
            event.printStackTrace();
        }
        keyboard.close();
        System.out.println("System exists.");
        System.exit(0);
    }

    public static void comboArrayOfVehicles() {
        ArrayList<Vehicle> arrayListOfVehicles = new ArrayList<Vehicle>();
        try {
            arrayListOfVehicles.add(new Car(Vehicle.MakeName.AC, "Doom Slayer",
                    1000.0, 2000, 6));
            arrayListOfVehicles.add(new Car(Vehicle.MakeName.BAIC, "Dragoon Sport",
                    5050.650, 2020, 2));
            arrayListOfVehicles.add(new Plane(Vehicle.MakeName.BMW, "Pokemon Number 5",
                    635000.50, 2022, 248));
            arrayListOfVehicles.add(new Plane(Vehicle.MakeName.DAF, "MKF-758",
                    41000.0, 1994, 700));
            arrayListOfVehicles.add(new Truck(Vehicle.MakeName.DS, "Armored Cat",
                    21000.10, 1954, 4, 5000));
            arrayListOfVehicles.add(new Truck(Vehicle.MakeName.GAZ, "ADN-1563",
                    25000.25, 2004, 3, 12500));
        } catch (Exception event) {
            wrapCatch("Something went wrong while constructing vehicles:", event);
        }

        for (int index = 0; index < arrayListOfVehicles.size(); index++) {
            Vehicle vehicle = arrayListOfVehicles.get(index);
            int lengthOfArrayOfPassengers = vehicle.getLengthOfArrayOfPassengers();
            for (int count = 0; count < lengthOfArrayOfPassengers; count++) {
                try {
                    vehicle.hop("Man " + String.valueOf(count + 1));
                } catch (Exception event) {
                    wrapCatch("The vehicle " + vehicle.getModel()
                            + " isn't in a good condition while passengers hopping:", event);
                }
            }

            int indexOfDriver = (int) (Math.random() * lengthOfArrayOfPassengers);
            try {
                vehicle.setDriver(indexOfDriver);
            } catch (Exception event) {
                wrapCatch("The vehicle " + vehicle.getModel() + " isn't in a good condition while setting driver:",
                        event);
            }

            vehicle.displayWindowSticker();
        }
    }

    public static void main(String[] arguments) {
        comboArrayOfVehicles();

        System.exit(1);
    }
}
