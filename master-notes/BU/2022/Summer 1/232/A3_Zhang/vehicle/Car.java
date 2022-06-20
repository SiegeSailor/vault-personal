package vehicle;

import exception.*;
import service.*;

/* File: Car.java */

/**
 * Edit History:
 * 06/19/2022 Jin Yu, Zhang Initial version.
 * 06/20/2022 Jin Yu, Zhang Add public methods.
 */

/**
 * The class for Car. A sub class from Vehicle.
 * 
 * @author Jin Yu, Zhang
 */
public class Car extends Vehicle {
    /** Drive mode. */
    public enum DriveMode {
        /** Manual */
        Manual,
        /** Automatic */
        Automatic,
        /** Initial */
        Initial
    }

    private DriveMode mode;

    private int numberOfDoors;

    /**
     * Setter for numberOfDoors. The input should be positive.
     * 
     * @param input {@code int} Number of doors.
     * @throws ValidationException.NegativeInteger When input is negative.
     */
    private void setNumberOfDoors(int input) throws ValidationException.NegativeInteger {
        Validation.integerNonNegative("Number of Doors", input);
        this.numberOfDoors = input;
    }

    /**
     * Construct a car with necessary data. Drive mode will be set to
     * Initial and numberOfDoors will be as same as numberOfPassengers.
     * 
     * @param initialMake           {@code MakeName} Make.
     * @param initialModel          {@code String} Model.
     * @param initialPrice          {@code double} Price. Should be positive.
     * @param initialProductionYear {@code int} Production year. Should be positive.
     * @param numberOfPassengers    {@code int} Array size for arrayOfPassengers.
     *                              Should be positive.
     * @throws ValidationException.InvalidString   When initialModel's length is 0.
     * @throws ValidationException.NullObject      When make is null.
     * @throws ValidationException.NegativeDouble  When price is negative.
     * @throws ValidationException.InvalidDate     When production year is in the
     *                                             future or earlier that
     *                                             FIRST_VEHICLE_PATENT_YEAR due to
     *                                             obvious reason.
     * @throws ValidationException.FullArray       When there is already an
     *                                             arrayOfPassenger.
     * @throws ValidationException.NotEnough       When there are passengers inside
     *                                             the vehicle.
     * @throws ValidationException.NegativeInteger When numberOfPassengers are
     *                                             negative.
     */
    public Car(MakeName initialMake, String initialModel, double initialPrice, int initialProductionYear,
            int numberOfPassengers) throws ValidationException.InvalidString, ValidationException.NullObject,
            ValidationException.NegativeDouble, ValidationException.InvalidDate, ValidationException.FullArray,
            ValidationException.NotEnough, ValidationException.NegativeInteger {
        super(initialMake, initialModel, initialPrice, initialProductionYear, numberOfPassengers);
        setMode(DriveMode.Initial);
        setNumberOfDoors(numberOfPassengers);
    }

    /**
     * Print all the data inherited from Vehicle and from the car self.
     */
    @Override
    public void displayWindowSticker() {
        super.displayWindowSticker();
        System.out.println(
                "Class: " + this.getClass().getSimpleName() + "\nMode: " + getMode().toString()
                        + "\nNumber of Doors: " + getNumberOfDoors());
    };

    /**
     * Getter for mode.
     * 
     * @return The car's current drive mode.
     */
    public DriveMode getMode() {
        return this.mode;
    }

    /**
     * Setter for mode.
     * 
     * @param input {@code DriveMode} Mode. Can't be null.
     * @throws ValidationException.NullObject When input is null.
     */
    public void setMode(DriveMode input) throws ValidationException.NullObject {
        Validation.objectMeaningful("Mode", input);
        this.mode = input;
    }

    /**
     * Getter for numberOfDoors.
     * 
     * @return Number of doors.
     */
    public int getNumberOfDoors() {
        return this.numberOfDoors;
    }
}
