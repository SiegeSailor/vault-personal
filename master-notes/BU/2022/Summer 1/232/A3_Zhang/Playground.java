
import java.util.*;

import exception.ValidationException;
import vehicle.Car;
import vehicle.Plane;
import vehicle.Truck;
import vehicle.Vehicle;

/* File: Playground.java */

/**
 * Edit History:
 * 06/19/2022 Jin Yu, Zhang Initial version.
 * 06/20/2022 Jin Yu, Zhang Add context and documentations.
 */

/**
 * Driver.
 * 
 * @author Jin Yu, Zhang
 */
public class Playground {
    /**
     * Wrap exception catch with control flow.
     * 
     * @param description {@code String} Text to describe why such exception happen.
     * @param event       {@code Exception} Caught exception.
     */
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

    /**
     * Create passenger name with index as it is not necessary to create names
     * like John, Tom.
     * 
     * @param index {@code index} Identity.
     * @return "Man index"
     */
    public static String createPassengerName(int index) {
        return "Man " + index;
    }

    /**
     * Get necessary information to identify a vehicle.
     * 
     * @param input {@code Vehicle} The vehicle.
     * @return "make model"
     */
    public static String createVehicleName(Vehicle input) {
        return input.getMake().toString() + " " + input.getModel();
    }

    /**
     * Default constructor.
     */
    public Playground() {
    }

    /**
     * Actions to demonstrate.
     * 
     * @param arguments {@code String[]} Implemented but not used.
     */
    public static void main(String[] arguments) {
        System.out.println("\n=== Vehicle Public Methods ===");
        ArrayList<Vehicle> arrayListOfVehicles = new ArrayList<Vehicle>();
        try {
            arrayListOfVehicles.add(new Car(Vehicle.MakeName.TVR, "Doom Slayer",
                    1000.0, 2000, 6));
            arrayListOfVehicles.add(new Plane(Vehicle.MakeName.GAZ, "Pokemon Number 5",
                    635000.50, 2022, 248));
            arrayListOfVehicles.add(new Truck(Vehicle.MakeName.SEAT, "Armored Cat",
                    21000.10, 1954, 4, 5000));
            arrayListOfVehicles.add(new Car(Vehicle.MakeName.MG, "Dragoon Sport",
                    5050.650, 2020, 2));
            arrayListOfVehicles.add(new Plane(Vehicle.MakeName.DAF, "MKF-758",
                    41000.0, 1994, 700));
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
                    vehicle.hop(createPassengerName(count + 1));
                } catch (Exception event) {
                    wrapCatch(createVehicleName(vehicle)
                            + " isn't in a good condition while passengers hopping:", event);
                }
            }

            int indexOfDriver = (int) (Math.random() * lengthOfArrayOfPassengers);
            try {
                vehicle.setDriver(indexOfDriver);
            } catch (Exception event) {
                wrapCatch(createVehicleName(vehicle) + " isn't in a good condition while setting driver:",
                        event);
            }
        }

        System.out.println("\nSTEP 1 / Display:");
        for (Vehicle vehicle : arrayListOfVehicles) {
            vehicle.displayWindowSticker();
        }

        System.out.println("\nSTEP 2 / Start:");
        for (Vehicle vehicle : arrayListOfVehicles) {
            try {
                vehicle.start();
                System.out.println(createVehicleName(vehicle) + " started by "
                        + vehicle.getDriver() + ".");
            } catch (Exception event) {
                wrapCatch(createVehicleName(vehicle) + " isn't in a good condition to start:",
                        event);
            }
        }

        System.out.println("\nSTEP 3 / Stop:");
        for (Vehicle vehicle : arrayListOfVehicles) {
            try {
                vehicle.stop();
                System.out.println(createVehicleName(vehicle) + " stopped by "
                        + vehicle.getDriver() + ".");
            } catch (Exception event) {
                wrapCatch(createVehicleName(vehicle) + " isn't in a good condition to stop:",
                        event);
            }
        }

        System.out.println("\nSTEP 4 / Drop:");
        for (Vehicle vehicle : arrayListOfVehicles) {
            try {
                int indexOfRandomPassenger = (int) (Math.random() *
                        vehicle.getLengthOfArrayOfPassengers());
                String leavingPassenger = createPassengerName(indexOfRandomPassenger + 1);
                vehicle.drop(leavingPassenger);
                System.out.println(leavingPassenger + " leaved from " + createVehicleName(vehicle) + "."
                        + (vehicle.getDriver() == null
                                ? " The passenger is also the driver."
                                : ""));
            } catch (Exception event) {
                wrapCatch(createVehicleName(vehicle) + " isn't in a good condition to start:",
                        event);
            }
        }

        System.out.println("\nSTEP 5 / Comparable:");
        Collections.sort(arrayListOfVehicles);
        for (Vehicle vehicle : arrayListOfVehicles) {
            System.out.println(createVehicleName(vehicle));
        }

        System.out.println("\nSTEP 6 / Set Passengers:");
        for (Vehicle vehicle : arrayListOfVehicles) {
            try {
                vehicle.setArrayOfPassengers(vehicle.getLengthOfArrayOfPassengers());
            } catch (ValidationException.FullArray event) {
                System.out.println("There are " + vehicle.getLengthOfArrayOfPassengers() + " slots for passengers on "
                        + createVehicleName(vehicle) + ". Action cancelled.");
            } catch (ValidationException.NotEnough event) {
                wrapCatch(createVehicleName(vehicle) + " isn't in a good condition to set passengers:",
                        event);
            }
        }

        System.out.println("\n=== Sub Class Public Methods ===");
        for (Vehicle vehicle : arrayListOfVehicles) {
            System.out.println(
                    "\n" + vehicle.getClass().getSimpleName() + " / " + createVehicleName(vehicle) + ":");
            switch (vehicle.getClass().getSimpleName()) {
                case "Car":
                    Car car = (Car) vehicle;
                    System.out.println("The car is on " + car.getMode() + " mode.");
                    try {
                        car.setMode(Car.DriveMode.Automatic);
                        System.out.println("The car has been set to " + car.getMode() + " mode.");
                        car.setMode(Car.DriveMode.Manual);
                        System.out.println("The car has been set to " + car.getMode() + " mode.");
                        System.out.println("There are " + car.getNumberOfDoors() + " doors on the car.");
                    } catch (Exception event) {
                        wrapCatch(createVehicleName(vehicle) + " isn't in a good condition to switch its mode:", event);
                    }
                    break;
                case "Truck":
                    Truck truck = (Truck) vehicle;
                    System.out.println(
                            "The truck's load/capacity is " + truck.getLoad() + "/" + truck.getCapacity() + ".");
                    try {
                        truck.add(truck.getCapacity() / 2);
                        System.out.println(
                                "The truck is half full. (" + truck.getLoad() + "/" + truck.getCapacity() + ")");
                        truck.remove(50);
                        System.out.println(
                                "The truck is a little bit lighter. (" + truck.getLoad() + "/" + truck.getCapacity()
                                        + ")");
                    } catch (Exception event) {
                        wrapCatch(createVehicleName(vehicle) + " isn't in a good condition to add or remove:", event);
                    }
                    break;
                case "Plane":
                    Plane plane = (Plane) vehicle;
                    System.out.println(
                            "The plane has " + plane.getNumberOfStatusEngine(Plane.EngineStatus.OFF) + " engines off.");
                    for (int index = 0; index < plane.getNumberOfEngines(); index++) {
                        try {
                            plane.engineOn(index);
                            System.out.println("Engine [" + index + "] is on.");
                            plane.engineOff(index);
                            System.out.println("Engine [" + index + "] is off.");
                        } catch (Exception event) {
                            wrapCatch(
                                    createVehicleName(vehicle) + " isn't in a good condition to turn on/off an engine:",
                                    event);
                        }
                    }
                    System.out.println("There are " + plane.getNumberOfWindows() + " windows on the plane.");
                    break;
            }
        }

        System.exit(1);
    }
}
